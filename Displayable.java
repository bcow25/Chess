import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
public abstract class Displayable implements Comparable<Displayable> {
    private static ArrayList<Displayable> displays=new ArrayList<Displayable>();
    protected Point pos;
    abstract protected Image getImage();
    public Displayable() {
        displays.add(this);
    }
    public static final void drawAll(Graphics g) {
        displays.sort(null);
        for(Displayable d:displays) {
            d.draw(g);
        }
    }
    public void draw(Graphics g,Point p) {
        Image i=getImage();
        g.drawImage(i,p.x-Game.get().camera().x+Game.get().getWidth()/2-i.getWidth(Game.get())/2,p.y-Game.get().camera().y+Game.get().getHeight()/2-i.getHeight(Game.get())/2,Game.get());
    }
    public void draw(Graphics g) {
        draw(g,pos);
    }
    public int compareTo(Displayable d) {
        return pos.y-d.pos.y;
    }
}
