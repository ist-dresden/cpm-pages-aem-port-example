package composum.prototype.aemwcmcorereplacement.aicodegen;

import static composum.prototype.aemwcmcorereplacement.aitasks.AIFileRepository.HTML_PATTERN;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

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

    public static final File ROOT_DIRECTORY = new File("../..");

    public static final String RELPATH_JCR_ROOT = "src/main/content/jcr_root";
    private static final Logger LOG = LoggerFactory.getLogger(RunWcmComponentsCodeGeneration.class);

    protected final AIFileRepository jcrContentApps = AIFileRepository.fromPath("../package", RELPATH_JCR_ROOT);

    protected final AIFileRepository aiPrompts = AIFileRepository.fromPath("src/test/resources/aiprompts");

    protected final AIFileRepository javaDstDir = AIFileRepository.fromPath("src/aigenerated/java");

    protected final AIFileRepository javaScrDir = AIFileRepository.fromPath("src/main/java");

    protected final Supplier<ChatCompletionBuilder> chatBuilderFactory =
            () -> new ChatCompletionBuilder().model("gpt-4-turbo-preview");

    public static void main(String[] args) {
        try {
            new RunWcmComponentsCodeGeneration().run();
        } finally {
            LOG.info("Done.");
        }
    }

    protected void run() {
        // generateTextModel();
        generateModels();
    }

    /**
     * Preliminary test: create a model class for text. Inputs:
     * package/src/main/content/jcr_root/apps/core/wcm/components/text/v2/text/README.md
     */
    protected void generateTextModel() {
        String modelClass = "com.adobe.cq.wcm.core.components.models.Text";
        File systemMessage = aiPrompts.file("generalsystemmessage.prompt");
        File componentReadme = jcrContentApps.file("apps/core/wcm/components/text/v2/text/README.md");
        List<File> componentHTL = jcrContentApps.files("apps/core/wcm/components/text/v2/text", HTML_PATTERN, false);
        File createSpecPrompt = aiPrompts.file("generateModelAttributeList.md");
        File specFile = javaDstDir.javaMdFile(modelClass);
        AITask createModelDescription = new AITask()
                .setSystemMessage(systemMessage)
                .addInputFile(componentReadme)
                .addInputFiles(componentHTL)
                .setPrompt(createSpecPrompt, "MODELCLASS", modelClass)
                .setOutputFile(specFile)
                .execute(this.chatBuilderFactory, ROOT_DIRECTORY);
//        String question = "What about      data-cmp-data-layer=\"${textModel.data.json}\" - why did we not include this? What do I need to change in the prompt so that it'd get included?";
//        String explanation = createModelDescription.explain(chatBuilderFactory, ROOT_DIRECTORY,
//                question);
//        System.out.printf("Explanation of %s%n%n%s%n", question, explanation);

        File parentClassFile = javaScrDir.javaFile("com.adobe.cq.wcm.core.components.models.AbstractComponent");
        File javaFile = javaDstDir.javaFile(modelClass);
        File createJavaPrompt = aiPrompts.file("generateModelClass.md");
        AITask createJavaClass = new AITask()
                .setSystemMessage(systemMessage)
                .addInputFiles(componentHTL)
                .addInputFile(parentClassFile)
                .addInputFile(specFile)
                .setPrompt(createJavaPrompt, "MODELCLASS", modelClass)
                .setOutputFile(javaFile)
                .execute(this.chatBuilderFactory, ROOT_DIRECTORY);
    }

    protected final List<String> modelClassesWithMainComponentDir = List.of(
            // "com.adobe.cq.wcm.core.components.models.Title title/v2/title",
            // "com.adobe.cq.wcm.core.components.models.Text text/v2/text",
            "com.adobe.cq.wcm.core.components.models.Separator separator/v1/separator"
    );

    protected void generateModels() {
        for (String pair : modelClassesWithMainComponentDir) {
            String modelCLass = pair.split("\\s+")[0].trim();
            String componentDir = pair.split("\\s+")[1].trim();
            File systemMessage = aiPrompts.file("generalsystemmessage.prompt");
            File componentReadme = jcrContentApps.file("apps/core/wcm/components/" + componentDir + "/README.md");
            List<File> componentHTL = jcrContentApps.filesContaining("apps/core/wcm/components/", HTML_PATTERN, Pattern.quote(modelCLass), true);
            if (componentHTL.isEmpty()) {
                throw new IllegalStateException("No HTL files found for " + modelCLass);
            }
            File createSpecPrompt = aiPrompts.file("generateModelAttributeList.md");
            File specFile = javaDstDir.javaMdFile(modelCLass);
            AITask createModelDescription = new AITask()
                    .setSystemMessage(systemMessage)
                    .addInputFile(componentReadme)
                    .addInputFiles(componentHTL)
                    .setPrompt(createSpecPrompt, "MODELCLASS", modelCLass)
                    .setOutputFile(specFile)
                    .execute(this.chatBuilderFactory, ROOT_DIRECTORY);

            File parentClassFile = javaScrDir.javaFile("com.adobe.cq.wcm.core.components.models.AbstractComponent");
            File javaFile = javaDstDir.javaFile(modelCLass);
            File createJavaPrompt = aiPrompts.file("generateModelClass.md");
            AITask createJavaClass = new AITask()
                    .setSystemMessage(systemMessage)
                    .addInputFiles(componentHTL)
                    .addInputFile(parentClassFile)
                    .addInputFile(specFile)
                    .setPrompt(createJavaPrompt, "MODELCLASS", modelCLass)
                    .setOutputFile(javaFile)
                    .execute(this.chatBuilderFactory, ROOT_DIRECTORY);
        }
    }

}
