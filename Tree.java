import java.awt.Image;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.File;
public class Tree extends Animatable {
    private Collider collider;
    public Tree(Point pos) {
        loadAnimations();
        this.pos=pos;
        collider=new Collider(pos,150,350);
    }
    protected void loadAnimations() {
        try {
        Image[] suicide=new Image[2];
        suicide[0] = ImageIO.read(new File("images/Trees-a.png"));
        suicide[1] = ImageIO.read(new File("images/Trees-b.png"));
        while(suicide[0]==null||suicide[1]==null); //most sane code written by tracy
        current=new Animator(suicide,200);
    } catch (Exception e) {System.out.println(e);}
    }
    public void tick() {
        if(Player.get().getCollider().isColliding(collider)) {
            if(Math.abs(Player.get().xvel)>Math.abs(Player.get().yvel)){
            if(Player.get().xvel!=0) Player.get().getPos().x=pos.x-Math.abs(Player.get().xvel)/Player.get().xvel*(collider.getW()+Player.get().getCollider().getW()+5)/2;
            Player.get().xvel=0;
            } else {
            if(Player.get().yvel!=0) Player.get().getPos().y=pos.y-Math.abs(Player.get().yvel)/Player.get().yvel*(collider.getH()+Player.get().getCollider().getH()+5)/2; 
                Player.get().yvel=0;
        }
            
        }
    }
}
