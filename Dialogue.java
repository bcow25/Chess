import java.util.Map;
public class Dialogue  {
    private Map<String, Dialogue> options;
    private String text;
    private Character speaker;
    public Dialogue(String text,Character speaker,Map<String,Dialogue>options) {
        this.text=text;
        this.speaker=speaker;
        this.options=options;
    }
}