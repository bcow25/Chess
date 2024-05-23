import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ChessPlayer implements Tickable {
    private static ChessPlayer c;
    private Collider collider;
    private static Image star=null;
    public static void create(int d) {
        c=new ChessPlayer(d);
        if(star==null) {
            try {
                star=ImageIO.read(new File("images/Star.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static ChessPlayer get() {
        return c;
    }
    private ChessPlayer(int d) {
        new Board(d);
        collider=new Collider(new Point(),400,400);
    }
    private Piece holdPiece;
    public void tick() {
        //System.out.println("hellp");
        if(Game.get().getE()&&collider.isColliding(Player.get().getCollider())) {
            Piece[][] pieces=Board.getBoard();
            Game.get().setE();
            int[] pos=getPlayerCoordinate();
            System.out.println(pos[0]+", "+pos[1]);
            if(holdPiece==null&&pieces[pos[0]][pos[1]]!=null) {
                holdPiece=pieces[pos[0]][pos[1]];
                if(holdPiece.pieceColor==false) holdPiece=null;
            } else {}
        }

    }
    public void draw(Graphics g) {
        Piece[][] pieces=Board.getBoard();
        for(Piece[] r:pieces) 
            for(Piece c:r) 
                if(c!=null&&c!=holdPiece) c.draw(g);
        drawStar(g,0,0);    
    }
    private void drawStar(Graphics g, int r, int c) {
        Displayable.draw(star,g,new Point(rToX(r),cToY(c)));
    }
    public static int rToX(int r) {
        return r*Board.squareSize+Board.borderSizeH;
    }
    public static int cToY(int c) {
        return c*Board.squareSize+Board.borderSizeW;
    }
    private int[] getPlayerCoordinate() {
        return(new int[]{(Player.get().getPos().y+200)/Board.squareSize,(Player.get().getPos().x+200)/Board.squareSize});
    }
}
