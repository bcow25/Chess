import java.util.ArrayList;
public class Dialogue  {
    public ArrayList<Option> getOptions() {
        return options;
    }
    private ArrayList<Option> options;
    public String getText() {
        return text;
    }
    private String text;
    public Character getSpeaker() {
        return speaker;
    }
    private Character speaker;
    public Dialogue(String text,ArrayList<Option> options) {
        //System.out.println("im so happy to be here");
        this.text=text;
        this.options=options;
    }
}
