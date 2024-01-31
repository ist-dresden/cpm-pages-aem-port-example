package composum.prototype.aemwcmcorereplacement.simpleOpenAIClient;

public class ChatCompletionBuilderRun {

    public static void main(String[] args) {
        String response = new ChatCompletionBuilder().systemMsg("Hello").userMsg("How are you?").execute();
        System.out.println(response);
    }
}
