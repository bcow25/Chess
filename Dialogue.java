import java.util.List;
public class Dialogue  {
    public List<Option> getOptions() {
        return options;
    }
    private List<Option> options;
    public String getText() {
        return text;
    }
    private String text;
    public Character getSpeaker() {
        return speaker;
    }
    private Character speaker;
    public Dialogue(String text,List<Option> options) {
        this.text=text;
        this.options=options;
    }
}
