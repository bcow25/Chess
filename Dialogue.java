import java.util.ArrayList;
public class Dialogue  {
    public ArrayList<Option> getOptions() {
        return options;
    }
    private ArrayList<Option> options;
    public String getText() {
        return text.getFrame();
    }
    //private String text;
    public Character getSpeaker() {
        return speaker;
    }
    private Character speaker;
    private TextAnimator text;
    public void reset() {
        text.reset();
    }
    public Dialogue(String text,ArrayList<Option> options) {
        //System.out.println("im so happy to be here");
        //this.text=text;
        this.options=options;
        this.text=new TextAnimator(text);
    }
}
