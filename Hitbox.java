import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
public class Hitbox implements Tickable {
    private Collider collider;
    public Hitbox(String str,Point pos) {
        try {
                Image img = ImageIO.read(new File(str));
                collider=new Collider(pos,img.getWidth(Game.get()),img.getHeight(Game.get()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    public Hitbox(Point pos, int w, int h) {
        collider=new Collider(pos,w,h);
    }   
    public void tick() {
        collider.pushPlayer();
    }
}
