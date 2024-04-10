import java.util.Map;
public class Dialogue  {
    public Map<String, Dialogue> getOptions() {
        return options;
    }
    private Map<String, Dialogue> options;
    public String getText() {
        return text;
    }
    private String text;
    public Character getSpeaker() {
        return speaker;
    }
    private Character speaker;
    public Dialogue(String text,Character speaker,Map<String,Dialogue>options) {
        this.text=text;
        this.speaker=speaker;
        this.options=options;
    }
}