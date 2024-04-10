igitmport java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.text.JTextComponent.KeyBinding;

import java.io.File;
import java.awt.Point;
import java.awt.event.KeyEvent;
public class NPC extends Character {
    private static Dialogue speaking=null;
    private String name;
    private Dialogue dialogue;
    public Dialogue getDialogue() {
        return dialogue;
    }
    private Collider pusher;
    private Collider talk;
    protected void loadAnimations() {
        try {
        Image[] suicide=new Image[2];
        suicide[0] = ImageIO.read(new File("images/npc1.png"));
        suicide[1] = ImageIO.read(new File("images/npc2.png"));
        while(suicide[0]==null||suicide[1]==null); //most sane code written by tracy
        walkU=walkD=walkR=walkL=idle=current=new Animator(suicide,200);
        } catch (Exception e) {System.out.println(e);}
    }
    public NPC(String name, Point pos, Dialogue dialogue) {
        loadAnimations();
        this.pos=pos;
        this.name=name;
        pusher=new Collider(pos,50,50);
        talk=new Collider(pos,70,70);  
        this.dialogue=dialogue;      
    }
    public void tick() {
        pusher.pushPlayer();
        if(talk.isColliding(Player.get().getCollider())) {
            if(Game.get().getLastKeyPressed()==KeyEvent.VK_E) {
                if(speaking==null) speaking=dialogue;
            }
        }
    }
    public String getName() {
        return name;
    }
}
