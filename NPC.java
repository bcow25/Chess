import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Point;
import java.awt.*;
import java.util.List;
public class NPC extends Character {
    private static Dialogue speaking=null;
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
    public NPC(String name, Point pos) {
        super(name,pos,0,0,0,0);
        loadAnimations();
        this.pos=pos;
        pusher=new Collider(pos,50,50);
        talk=new Collider(pos,70,70);      
    }
    public NPC(String name, Point pos, Dialogue dialogue) {
        this(name,pos);
        //System.out.println(dialogue);
        setDialogue(dialogue);
    }
    public void setDialogue(Dialogue dialogue) {
        this.dialogue=dialogue;
    }
    public void tick() {
        pusher.pushPlayer();
        if(talk.isColliding(Player.get().getCollider())) {
            if(Game.get().getE()) {
                //System.out.println("e fired");
                if(speaking==null) {setCurrent(dialogue);
                    Game.get().setE();
                    //System.out.println("speaking,,, hello???");
                }
            }
        }
    }
    public static void dialogueHandler() {
        if(speaking==null) return;
        //display the dialogue
        //listen for player option selection
        //System.out.println("speaking isnt null!!");
        int playerOption=playerOption();
        if(playerOption!=-1) {
            if(speaking.getOptions()!=null)
            setCurrent(speaking.getOptions().get(playerOption).getDialogue());
            else speaking=null;
        }
    }
    /**
     * precondition: speaking isnt null
       */
    //unfinished helper method will do one day :)
    //returns the index of the option that the player chose when talking
    //your mom
    //returns -1 if player has not selected anything yet
    public static int playerOption() {
        return Game.get().getOptionKey()-1;
    }
    public static void displayDialogue(Graphics g) {
        if(speaking==null) return;
        Point pos=new Point(0,500);
        g.setColor(new Color(55, 55, 55));
        g.fillRect( pos.x, 
                        pos.y, 
                      300, 100);
                      Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Courier", Font.BOLD, 25));
     g2d.drawString(speaking.getText(), pos.x, pos.y+50);
     List<Option> options=speaking.getOptions();
     //System.out.println(options);
     if(options==null) return;
     for(Option option:options)
         g2d.drawString(option.getString(), 300, pos.y=pos.y-50);
    }
    public String getName() {
        return name;
    }
    private static void setCurrent(Dialogue d) {
        speaking=d;
        d.reset();
    }
}
