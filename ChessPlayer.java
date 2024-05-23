import java.awt.*;
public class ChessPlayer implements Tickable {
    private static ChessPlayer c;
    private Collider collider;
    public static void create(int d) {
        System.out.println("hi");
        c=new ChessPlayer(d);

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
        System.out.println("hellp");
        if(Game.get().getE()&&collider.isColliding(Player.get().getCollider())) {
            Game.get().setE();
            int[] pos=getPlayerCoordinate();
            System.out.println(pos[0]+", "+pos[1]);
            if(holdPiece!=null) {

            }
        }

    }
    public void draw(Graphics g) {
        Piece[][] pieces=Board.getBoard();
        for(Piece[] r:pieces) 
            for(Piece c:r) 
                if(c!=null&&c!=holdPiece) c.draw(g);    
    }
    private int[] getPlayerCoordinate() {
        return(new int[]{(Player.get().getPos().x-Board.borderSizeW)/Board.squareSize,(Player.get().getPos().y-Board.borderSizeH)/Board.squareSize});
    }
}
