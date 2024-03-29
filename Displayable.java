import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
public abstract class Displayable {
    protected Point pos;
    abstract protected Image getImage();
    public void draw(Graphics g,Point p) {
        Image i=getImage();
        g.drawImage(i,p.x-Game.get().camera().x+Game.get().getWidth()/2-i.getWidth(Game.get())/2,p.y-Game.get().camera().y+Game.get().getHeight()/2-i.getHeight(Game.get())/2,Game.get());
    }
    public void draw(Graphics g) {
        draw(g,pos);
    }
}
