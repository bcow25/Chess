import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.util.List;
public class NPC extends Character {
    private static Dialogue speaking=null;
    private static boolean talked; //display stuff for talk;
    private Image big;
    private Dialogue dialogue;
    public Dialogue getDialogue() {
        return dialogue;
    }
    private Collider talk;
    protected void loadAnimations() {
        try {
            big=ImageIO.read(new File("images/NPC/Big_"+name+".png"));
            Image x=ImageIO.read(new File("images/NPC/"+name+".png"));
            collider.setH(x.getHeight(Game.get()));
            collider.setW(x.getWidth(Game.get()));
        walkU=walkD=walkR=walkL=idle=current=new Animator(new Image[]{x},200);
        } catch (Exception e) {e.printStackTrace();}
        
    }
    public NPC(String name, Point pos) {
        super(name,pos,0,0,0,0);
        loadAnimations();
        this.pos=pos;
        talk=new Collider(pos,90,90);      
    }
    public NPC(String name, Point pos, Dialogue dialogue) {
        this(name,pos);
        //System.out.println(dialogue);
        setDialogue(dialogue);
        dialogue.setSpeaker(this);
    }
    public void setDialogue(Dialogue dialogue) {
        this.dialogue=dialogue;
    }
    public void tick() {
        collider.pushPlayer();
        if(talk.isColliding(Player.get().getCollider())) {
            if(Game.get().getE()) {
                //System.out.println("e fired");
                if(speaking==null) {
                    if(!talked) {
                        new Messager("Press 1 or 2 to select your choice.",3000);
                        new Messager("If there are no choices, press either 1 or 2",3000);
                        talked=true;
                    }
                    setCurrent(dialogue);
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
            if(speaking.getOptions()!=null) {
                playerOption=Math.min(speaking.getOptions().size()-1,playerOption);
                setCurrent(speaking.getOptions().get(playerOption).getDialogue());
        }
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
        Point pos=new Point(0,420);
        g.drawImage(Dialogue.getImage(), 0, 410, Game.get());
        g.drawImage(speaking.getSpeaker().big,-50,250,Game.get());
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
        g2d.setColor(new Color(255, 255, 255));
        g2d.setFont(new Font("Courier", Font.BOLD, 20));
    String text=speaking.getText();
    int i=1;
    for(i=1;text.length()>31;i++) {
        g2d.drawString(text.substring(0,31), 175, pos.y+25*i);
        text=text.substring(31);
    }
    //System.out.println(i);
    
    g2d.drawString(text, 175, pos.y+25*i);
     
    g2d.setFont(new Font("Courier", Font.BOLD, 15));
    if(speaking==null) return;
     List<Option> options=speaking.getOptions();
     //System.out.println(options);
     if(options==null) return;
     for(Option option:options) {
        text=option.getString();
        i=1;
        for(i=1;text.length()>15;i++) {
            g2d.drawString(text.substring(0,15), 560, pos.y+15*i);
            text=text.substring(15);
        }
        //System.out.println(i);
        
        g2d.drawString(text, 560, pos.y+15*i);
        pos.y+=15*i;
         
     }
        // g2d.drawString(option.getString(), 560, pos.y=pos.y+50);
    }
    public String getName() {
        return name;
    }
    private static void setCurrent(Dialogue d) {
       if(speaking!=null) d.setSpeaker(speaking.getSpeaker());
        speaking=d;
        d.reset();
    }
    public static void clearCurrent() {
        speaking=null;
    }
}
