package composum.prototype.aemwcmcorereplacement.simpleOpenAIClient;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Build a chat completion request and execute it.
 * Usage: e.g. new ChatCompletionBuilder().model("gpt-4").systemMsg("Hello").userMsg("How are you?").build().toJson()
 * creates the corresponding JSON:
 */
public class ChatCompletionBuilder {

    private String model;
    private List<Message> messages = new ArrayList<>();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ChatCompletionBuilder model(String model) {
        this.model = model;
        return this;
    }

    public ChatCompletionBuilder systemMsg(String text) {
        if (text != null && !text.isEmpty()) {
            messages.add(new Message("system", new Content("text", text)));
        }
        return this;
    }

    public ChatCompletionBuilder userMsg(String text) {
        if (text != null && !text.isEmpty()) {
            messages.add(new Message("user", new Content("text", text)));
        }
        return this;
    }

//    public ChatCompletionBuilder userImage(String imageData) {
//        messages.add(new Message("user", new Content("image", imageData)));
//        return this;
//    }

    private ChatCompletionRequest build() {
        return new ChatCompletionRequest(model, messages, 0);
    }

    public String toJson() {
        return gson.toJson(build());
    }

    private static class ChatCompletionRequest {
        String model;
        List<Message> messages;
        double temperature;

        ChatCompletionRequest(String model, List<Message> messages, double temperature) {
            this.model = model;
            this.messages = messages;
            this.temperature = temperature;
        }
    }

    private static class Message {
        String role;
        Content content;

        Message(String role, Content content) {
            this.role = role;
            this.content = content;
        }
    }

    private static class Content {
        String type;
        String data;

        Content(String type, String data) {
            this.type = type;
            this.data = data;
        }
    }
}
