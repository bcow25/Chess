import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public abstract class Character implements Animatable {
    protected Animator current;
    protected Animator walkL;
    protected Animator walkU;
    protected Animator walkD;
    protected Animator walkR;
    protected Game game;
    //walk diagonals??
    public void draw(Graphics g,Point p) {
        Image i=current.getFrame();
        g.drawImage(i,p.x-game.camera().x+game.getWidth()/2-i.getWidth(game)/2,-game.camera().y+game.getHeight()/2-i.getHeight(game)/2,game);
    }
}
