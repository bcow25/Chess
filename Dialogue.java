import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Dialogue  {
    private static Image timg;
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
    protected TextAnimator text;
    public void reset() {
        text.reset();
    }
    public Dialogue(String text,ArrayList<Option> options) {
        //System.out.println("im so happy to be here");
        //this.text=text;
        this.options=options;
        this.text=new TextAnimator(text);
        if(timg==null) {
            try{timg=ImageIO.read(new File("images/Talking_Board.png"));
        }
            catch(Exception e) {
               // System.out.println("pain");
                e.printStackTrace();
        }}
    }
    public static Image getImage() {
        return timg;
    }
}
