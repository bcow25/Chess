import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
public abstract class Displayable {
    protected Game game;
    protected Point pos;
    abstract protected Image getImage();
    public void draw(Graphics g,Point p) {
        Image i=getImage();
        g.drawImage(i,p.x-game.camera().x+game.getWidth()/2-i.getWidth(game)/2,-game.camera().y+game.getHeight()/2-i.getHeight(game)/2,game);
    }
    public void draw(Graphics g) {
        draw(g,pos);
    }
}
