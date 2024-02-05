package composum.prototype.aemwcmcorereplacement.aicodegen;

import static composum.prototype.aemwcmcorereplacement.aitasks.AIFileRepository.HTML_PATTERN;

import java.io.File;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.aitasks.AIFileRepository;
import composum.prototype.aemwcmcorereplacement.aitasks.AITask;
import composum.prototype.aemwcmcorereplacement.simpleOpenAIClient.ChatCompletionBuilder;

/**
 * Uses AITasks to generate parts of the code for the partial port of the wcm.io core components.
 * We will normally run this manually from the IDE after changes seem necessary.
 * The results will be checked in with Git, which provides caching makes reasonably sure manual checks are done.
 * Start this in the root directory of the project this is in, e.g. $ModuleFileDir$ in IntelliJ IDEA.
 */
public class RunWcmComponentsCodeGeneration {

    public static final String RELPATH_JCR_ROOT = "src/main/content/jcr_root";
    private static final Logger LOG = LoggerFactory.getLogger(RunWcmComponentsCodeGeneration.class);
    public static final File ROOT_DIRECTORY = new File("../..");
    protected final AIFileRepository jcrContentApps = AIFileRepository.fromPath("../package", RELPATH_JCR_ROOT);

    protected final AIFileRepository aiPrompts = AIFileRepository.fromPath("src/test/resources/aiprompts");

    protected final AIFileRepository javaDstDir = AIFileRepository.fromPath("src/aigenerated/java");

    // FIXME(hps,02.02.24) use gpt-4 in the end.
    protected final Supplier<ChatCompletionBuilder> chatBuilderFactory =
            () -> new ChatCompletionBuilder().model("gpt-4-turbo-preview");

    public static void main(String[] args) {
        new RunWcmComponentsCodeGeneration().run();
        LOG.info("Done.");
    }

    protected void run() {
        generateTextModel();
    }

    /**
     * Preliminary test: create a model class for text. Inputs:
     * package/src/main/content/jcr_root/apps/core/wcm/components/text/v2/text/README.md
     */
    protected void generateTextModel() {
        String modelClass = "com.adobe.cq.wcm.core.components.models.Text";
        AITask createModelDescription = new AITask()
                .setSystemMessage(aiPrompts.file("generalsystemmessage.prompt"))
                .addInputFile(jcrContentApps.file("apps/core/wcm/components/text/v2/text/README.md"))
                .addInputFiles(jcrContentApps.files("apps/core/wcm/components/text/v2/text", HTML_PATTERN, false))
                .setPrompt(aiPrompts.file("generateModelAttributeList.md"), "MODELCLASS", modelClass)
                .setOutputFile(javaDstDir.javaMdFile(modelClass))
                .execute(this.chatBuilderFactory, ROOT_DIRECTORY);
        String question = "What about      data-cmp-data-layer=\"${textModel.data.json}\" - why did we not include this? What do I need to change in the prompt so that it'd get included?";
        String explanation = createModelDescription.explain(chatBuilderFactory, ROOT_DIRECTORY,
                question);
        System.out.printf("Explanation of %s%n%n%s%n", question, explanation);
    }

}
