package composum.prototype.aemwcmcorereplacement.aitasks;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

/**
 * We support the generation of files using an AI, specifically ChatGPT. A generation task can have several input files.
 * Some of them can be prompt files with task descriptions, and some of them source files to be processed. The output
 * of each task is one text file. A complex task can have several steps leading to several intermediate files.
 * <p>
 * Since ChatGPT is not particularily fast not free and the generation results have to be manually checked, this is
 * heavily cached.
 * Into each output file we write the versions of all the input files from which it was generated into a comment.
 * When the tasks are run, we compare the
 * versions of all the input files with the versions recorded in the comment, and only regenerate the output file if
 * the versions have changed. An input file can have a version comment that explicitly states the version, or we take the
 * an abbreviated SHA256 hash of the input file as version. It is possible to explicitly state the versions in
 * version comments in the input files to avoid regenerating all files if minor details e.g. in a prompt file are
 * changed - only when the prompt file version comment is changed everything is regenerated.
 * <p>
 * A version comment can e.g. look like this:
 * <p>
 * // AIGenVersion(ourversion, inputfile1@version1, inputfile2@version2, ...)
 * <p>
 * where ourversion and version1 and version2 are hashes. ourversion is the hash of the original output of the AI.
 * The comment syntax (in this case //) is ignored - we just look for the AIGenVersion via regex.
 * <p>
 * Normally the intermediate and final results should be checked in with Git. That ensures manual checks when
 * they are regenerated, and minimizes regeneration.
 */
public class AITask {

    private static final Logger LOG = LoggerFactory.getLogger(AITask.class);

    /**
     * A marker that can be inserted by the AI when something is wrong / unclear. We will make sure the user
     */
    public static final String FIXME = "FIXME";

    private List<File> inputFiles = new ArrayList<>();
    private File outputFile;
    private String prompt;

    public AITask addInputFile(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File " + file + " does not exist");
        }
        inputFiles.add(file);
        return this;
    }

    public AITask addOptionalInputFile(@Nullable File file) {
        if (file != null && file.exists()) {
            inputFiles.add(file);
        } else {
            LOG.debug("Optional file not there: {}", file);
        }
        return this;
    }

    public AITask addInputFiles(List<File> files) {
        for (File file : files) {
            addInputFile(file);
        }
        return this;
    }

    public AITask setOutputFile(@Nonnull File file) {
        if (file == null) {
            throw new RuntimeException("File must not be null");
        }
        outputFile = file;
        return this;
    }

    public void process(Function<List<File>, String> aiExecution) {
        if (!hasToBeRun()) {
            return;
        }
        String result = aiExecution.apply(inputFiles);
        String outputVersion = DigestUtils.sha256Hex(result);
        List<String> inputVersions = new ArrayList<>();
        for (File inputFile : inputFiles) {
            String version = calculateFileVersion(inputFile);
            inputVersions.add(inputFile.getName() + "@" + version);
        }
        AIVersionMarker aiVersionMarker = new AIVersionMarker(outputVersion, inputVersions);
        result = embedComment(result, aiVersionMarker.toString());

        String versionComment = "AIGenVersion(" + calculateFileVersion(outputFile);
        for (File inputFile : inputFiles) {
            versionComment += ", " + inputFile.getName() + "@" + calculateFileVersion(inputFile);
        }
        versionComment += ")";
        result = result.replaceFirst("AIGenVersion\\(.*\\)", versionComment);
        try {
            Files.write(result, outputFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + outputFile, e);
        }
    }

    protected String embedComment(String content, String comment) {
        if (outputFile.getName().endsWith(".java")) {
            return "// " + comment + "\n" + content;
        } else if (outputFile.getName().endsWith(".html")) {
            return content + "\n<!-- " + comment + " -->\n";
        } else if (outputFile.getName().endsWith(".css")) {
            return "/* " + comment + " */\n" + content;
        } else {
            return "# " + comment + "\n" + content;
        }
    }

    public boolean hasToBeRun() {
        if (!outputFile.exists()) {
            return true;
        }
        AIVersionMarker outputVersionMarker = getOutputVersionMarker();
        if (outputVersionMarker == null) {
            return true;
        }
        List<String> inputVersions = new ArrayList<>();
        for (File inputFile : inputFiles) {
            String version = calculateFileVersion(inputFile);
            inputVersions.add(inputFile.getName() + "@" + version);
        }
        return !inputVersions.equals(outputVersionMarker.getInputversions());
    }

    protected String calculateFileVersion(@Nonnull File file) {
        String content = getFileContent(file);
        if (content == null) {
            throw new RuntimeException("Could not read file " + file);
        }
        AIVersionMarker aiVersionMarker = AIVersionMarker.find(content);
        if (aiVersionMarker != null) {
            return aiVersionMarker.getOurVersion();
        }
        return DigestUtils.sha256Hex(content);
    }

    /**
     * Version of output file.
     */
    protected AIVersionMarker getOutputVersionMarker() {
        if (!outputFile.exists()) {
            return null;
        }
        String content = getFileContent(outputFile);
        if (content == null) {
            return null;
        }
        AIVersionMarker aiVersionMarker = AIVersionMarker.find(content);
        if (aiVersionMarker == null) {
            throw new RuntimeException("Could not find version marker in " + outputFile);
        }
        return aiVersionMarker;
    }

    protected String getFileContent(@Nonnull File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            return Files.toString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + file, e);
        }
    }

    /**
     * The actual prompt to be executed. The prompt file content can contain placeholders that are replaced by the values given: placeholdersAndValues contain alternatingly placeholder names and values for them.
     */
    public void setPrompt(@Nonnull File promptFile, String... placeholdersAndValues) {
        String prompt = getFileContent(promptFile);
        if (prompt == null) {
            throw new RuntimeException("Could not read prompt file " + promptFile);
        }
        if (placeholdersAndValues.length % 2 != 0) {
            throw new RuntimeException("Odd number of placeholdersAndValues");
        }
        for (int i = 0; i < placeholdersAndValues.length; i += 2) {
            prompt = prompt.replace(placeholdersAndValues[i], placeholdersAndValues[i + 1]);
        }
        this.prompt = prompt;
    }
}
