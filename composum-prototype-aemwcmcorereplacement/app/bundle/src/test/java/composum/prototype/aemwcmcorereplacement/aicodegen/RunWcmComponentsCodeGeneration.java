package composum.prototype.aemwcmcorereplacement.aicodegen;

import static composum.prototype.aemwcmcorereplacement.aitasks.AIFileRepository.HTML_PATTERN;

import java.io.File;
import java.util.function.Supplier;

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

    protected final AIFileRepository jcrContentApps = AIFileRepository.fromPath("../package", RELPATH_JCR_ROOT);

    protected final AIFileRepository aiPrompts = AIFileRepository.fromPath("src/test/resources/aiprompts");

    protected final AIFileRepository javaDstDir = AIFileRepository.fromPath("src/aigenerated/java");

    // FIXME(hps,02.02.24) use gpt-4 in the end.
    protected final Supplier<ChatCompletionBuilder> chatBuilderFactory =
            () -> new ChatCompletionBuilder().model("gpt-3.5-turbo");

    public static void main(String[] args) {
        new RunWcmComponentsCodeGeneration().run();
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
        AITask task = new AITask();
        task.addInputFile(jcrContentApps.file("apps/core/wcm/components/text/v2/text/README.md"));
        task.addInputFiles(jcrContentApps.files("apps/core/wcm/components/text/v2/text", HTML_PATTERN, false));
        task.setSystemMessage(aiPrompts.file("generalsystemmessage.prompt"));
        task.setPrompt(aiPrompts.file("generateModelAttributeList.md"), "MODELCLASS", modelClass);
        task.setOutputFile(javaDstDir.javaMdFile(modelClass));
        task.execute(this.chatBuilderFactory, new File("../.."));
    }

}
