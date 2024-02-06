package composum.prototype.aemwcmcorereplacement.simpleOpenAIClient;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Build a chat completion request and execute it.
 * Usage: e.g. new ChatCompletionBuilder().model("gpt-4").systemMsg("Hello").userMsg("How are you?").toJson()
 * creates the corresponding JSON:
 */
public class ChatCompletionBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ChatCompletionBuilder.class);

    private static final String CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private String model = "gpt-4-turbo-preview";
    private final List<Message> messages = new ArrayList<>();
    private String openAiApiKey;
    private int maxTokens = 1000;

    /**
     * Takes the openai key from environment variable OPENAI_API_KEY or system property openai.api.key .
     * Throws up if none of the is there.
     */
    public ChatCompletionBuilder() {
        openAiApiKey = System.getenv("OPENAI_API_KEY");
        if (openAiApiKey == null) {
            openAiApiKey = System.getProperty("openai.api.key");
        }
        Objects.requireNonNull(openAiApiKey, "openAiApiKey needed - not found in environment variable OPENAI_API_KEY or system property openai.api.key");
    }

    public ChatCompletionBuilder(String openAiApiKey) {
        Objects.requireNonNull(openAiApiKey, "openAiApiKey needed");
        this.openAiApiKey = openAiApiKey;
    }

    public ChatCompletionBuilder maxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
        return this;
    }

    public ChatCompletionBuilder model(String model) {
        this.model = model;
        return this;
    }

    public ChatCompletionBuilder systemMsg(String text) {
        if (text != null && !text.isEmpty()) {
            messages.add(new Message("system", text));
        }
        return this;
    }

    public ChatCompletionBuilder userMsg(String text) {
        if (text != null && !text.isEmpty()) {
            messages.add(new Message("user", text));
        }
        return this;
    }

    public ChatCompletionBuilder assistantMsg(String text) {
        if (text != null && !text.isEmpty()) {
            messages.add(new Message("assistant", text));
        }
        return this;
    }

    /**
     * Calls OpenAI chat completion service and returns the answer.
     */
    public String execute() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CHAT_COMPLETION_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + openAiApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(toJson()))
                .build();
        long start = System.currentTimeMillis();
        try {
            HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IllegalStateException("Unexpected status code " + response.statusCode() + " from OpenAI: " + response.body());
            }
            return extractResponse(response.body());
        } catch (java.io.IOException | java.lang.InterruptedException e) {
            throw new IllegalStateException("Could not send request to OpenAI", e);
        } finally {
            long duration = System.currentTimeMillis() - start;
            LOG.info("OpenAI request took {} ms", duration);
        }
    }

    public String toJson() {
        return gson.toJson(build());
    }

    private ChatCompletionRequest build() {
        return new ChatCompletionRequest(model, messages, 0, maxTokens);
    }

    /**
     * Extracts the actual response from the JSON response. Verifies that the finish_reason is stop.
     */
    private String extractResponse(String json) {
        ChatCompletionResponse chatCompletionResponse = gson.fromJson(json, ChatCompletionResponse.class);
        LOG.info("Usage: {}", chatCompletionResponse.usage);
        if (chatCompletionResponse.choices.isEmpty()) {
            throw new IllegalStateException("No choices in response: " + json);
        }
        ChatCompletionResponse.Choice choice = chatCompletionResponse.choices.get(0);
        if (!"stop".equals(choice.finish_reason)) {
            throw new IllegalStateException("Invalid finish reason: " + choice.finish_reason + " in response: " + json);
        }
        return choice.message.content;
    }

    private static class ChatCompletionRequest {
        String model;
        List<Message> messages;
        double temperature;
        int max_tokens;

        ChatCompletionRequest(String model, List<Message> messages, double temperature, int maxTokens) {
            this.model = model;
            this.messages = messages;
            this.temperature = temperature;
            this.max_tokens = maxTokens;
        }
    }

    private static class Message {
        String role;
        String content;

        Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    private static class ChatCompletionResponse {
        List<Choice> choices;
        Usage usage;

        private static class Choice {
            Message message;
            String finish_reason;
        }

        private static class Usage {
            int prompt_tokens;
            int completion_tokens;
            int total_tokens;

            @Override
            public String toString() {
                return "Usage{" +
                        "prompt_tokens=" + prompt_tokens +
                        ", completion_tokens=" + completion_tokens +
                        ", total_tokens=" + total_tokens +
                        '}';
            }
        }
    }
}
