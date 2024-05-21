import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
public class Hitbox extends Displayable implements Tickable {
    private Image img;
    public Hitbox(String str,Point pos) {
        super(pos,0,0,0,0);
        try {
                img = ImageIO.read(new File(str));
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        collider.setH(img.getHeight(Game.get()));
        collider.setW(img.getWidth(Game.get()));
        display.setH(collider.getH());
        display.setW(collider.getW());
    }
    
    public Image getImage() {return img;}
    public void tick() {
        collider.pushPlayer();
    }
}
