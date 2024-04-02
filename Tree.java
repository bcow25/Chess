import java.awt.Image;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.File;
public class Tree extends Animatable {
    private Collider collider;
    public Tree(Point pos) {
        loadAnimations();
        this.pos=pos;
        collider=new Collider(pos,20,70);
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
            Player.get().xvel*=-1;
            Player.get().yvel*=-1;
        }
    }
}
