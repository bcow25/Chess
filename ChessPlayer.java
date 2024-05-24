import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ChessPlayer implements Tickable {
    private static ChessPlayer c;
    private static Image star = null;

    public static void create(int d) {
        c = new ChessPlayer(d);
        if (star == null) {
            try {
                star = ImageIO.read(new File("images/Star.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ChessPlayer get() {
        return c;
    }

    private Collider collider;
    private ArrayList<int[]> legalMoves;

    private ChessPlayer(int d) {
        new Board(d);
        holdPiece=null;
        legalMoves=null;
        collider = new Collider(new Point(), 400, 400);
    }

    private Piece holdPiece;

    public void tick() {
        if(Game.get().getOptionKey()==1) {holdPiece=null;
        legalMoves=null;}
        if(Game.get().getOptionKey()==2) Board.setGameState(2);
        if(Board.getGameState()!=0) {
            if(Board.getGameState()==1) {
                new Messager("You won!");
                Player.get().addToInventory( new Plant()); 
                new Messager("You were awarded a seed. Go plant it!");
            } else {
                new Messager("You lost. Skill issue bozo");
            }
            Game.get().toScene(0);
        }
        if (Game.get().getE() && collider.isColliding(Player.get().getCollider())) {
            Piece[][] pieces = Board.getBoard();
            Game.get().setE();
            int[] pos = getPlayerCoordinate();
            if (holdPiece == null) {
                if (pieces[pos[0]][pos[1]] != null && pieces[pos[0]][pos[1]].pieceColor == true) {
                    holdPiece = pieces[pos[0]][pos[1]];
                    legalMoves = holdPiece.generateLegalMoves();
                }

            } else {
                for (int[] a : legalMoves) {
                    if (equalCoor(a, pos)) {
                        legalMoves = null;
                        holdPiece.move(pos[0], pos[1], false);
                        holdPiece = null;
                        //opponent move
                        Board.playBlackMove();
                        return;
                    }
                }
                new Messager("Not a legal move!");
            }
        }

    }

    public void draw(Graphics g) {
        Piece[][] pieces = Board.getBoard();
        for (Piece[] r : pieces)
            for (Piece c : r)
                if (c != null && c != holdPiece)
                    c.draw(g);
        if (holdPiece != null)
            holdPiece.draw(g, new Point(Player.get().getPos().x + 25, Player.get().getPos().y - 25));
        if (legalMoves != null) {
            for (int[] p : legalMoves)
                drawStar(g, p[0], p[1]);
        }
    }

    private void drawStar(Graphics g, int r, int c) {
        Displayable.draw(star, g, new Point(cToY(c), rToX(r)));
    }

    public static int rToX(int r) {
        return r * Board.squareSize + Board.borderSizeH;
    }

    public static int cToY(int c) {
        return c * Board.squareSize + Board.borderSizeW;
    }

    private int[] getPlayerCoordinate() {
        return (new int[] { (Math.max(Math.min(7,(Player.get().getPos().y + 200) / Board.squareSize),0)),
                Math.max(0,Math.min(7,(Player.get().getPos().x + 200) / Board.squareSize ))});
    }

    public static boolean equalCoor(int[] a, int[] b) {
        return a[0] == b[0] && b[1] == a[1];
    }
}
