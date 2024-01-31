package composum.prototype.aemwcmcorereplacement.simpleOpenAIClient;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ChatCompletionBuilderTest {

    @Test
    public void toJson() {
        String json = new ChatCompletionBuilder().model("gpt-4").systemMsg("Hello").userMsg("How are you?").toJson();
        assertThat(json, is("{\n" +
                "  \"model\": \"gpt-4\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"data\": \"Hello\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"data\": \"How are you?\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"temperature\": 0.0\n" +
                "}"));
    }

}
