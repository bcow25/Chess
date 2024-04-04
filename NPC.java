import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.awt.Point;
public class NPC extends Character {
    private String name;
    private ArrayList killme;
    private Collider pusher;
    private Collider talk;
    protected void loadAnimations() {
        try {
        Image[] suicide=new Image[2];
        suicide[0] = ImageIO.read(new File("images/np1.png"));
        suicide[1] = ImageIO.read(new File("images/npc2.png"));
        while(suicide[0]==null||suicide[1]==null); //most sane code written by tracy
        walkU=walkD=walkR=walkL=idle=current=new Animator(suicide,200);
        } catch (Exception e) {System.out.println(e);}
    }
    public NPC(String name, Point pos) {
        loadAnimations();
        this.pos=pos;
        pusher=new Collider(pos,50,50);
        talk=new Collider(pos,70,70);        
    }
}
