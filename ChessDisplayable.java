import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
public abstract class ChessDisplayable {
    protected Point pos;
    abstract protected Image getImage();
    public void draw(Graphics g,Point p) {
        Image i=getImage();
        g.drawImage(i,p.x+ChessBoard.get().getWidth()/2-i.getWidth(ChessBoard.get())/2,p.y+ChessBoard.get().getHeight()/2-i.getHeight(ChessBoard.get())/2,ChessBoard.get());
        //System.out.print(p.x + " " + p.y); 
    }
    public void draw(Graphics g) {
        draw(g,pos);
    }
}
