import java.util.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;


public abstract class Piece extends Displayable{
         protected Image killme;
    protected abstract void loadImage(); 
    protected Image getImage(){
            return killme; 
        }
        public Piece (int r, int c,boolean color){
            super(new Point(ChessPlayer.cToY(c),ChessPlayer.rToX(r)),0,0,0,0);
            row = r; 
            col = c;
            pieceColor=color;
            loadImage(); 
        }
        protected boolean pieceColor; //true means white
        protected int row;
        protected int col;
        
        public void move(int r,int c, boolean test) {
            if(Board.getBoard()[r][c] instanceof King) Board.setGameState(pieceColor?1:2);
            Board.getBoard()[r][c]=this;
            Board.getBoard()[row][col]=null;
            row=r;
            col=c;
            
        }
         //test if a move will put your king in check,out of bounds, or collides with your own piece
        //true means is a valid move
        protected boolean testMove(int r,int c) {
            if(r<0||r>7||c<0||c>7) return false;
            Piece p=Board.getBoard()[r][c];
            return (p==null||p.pieceColor!=pieceColor);
            /* 
            if(Board.getBoard()[r][c]!= null ){
                if (Board.getBoard()[r][c].pieceColor==this.pieceColor){
                    return false;
                }
                
            }
            Piece t=Board.getBoard()[r][c];
            int tr=row;
            int tc=col;
            
            move(r,c, true);
            boolean a=Board.inCheck();
            move(tr,tc, true);
            Board.getBoard()[r][c]=t;
            return !a;*/
        }
        public int[] generateRandomMoves(){
            
            ArrayList<int[]> possibleMoves = generateLegalMoves();
            return possibleMoves.get((int)(Math.random()*possibleMoves.size())); 
        }
        public abstract ArrayList<int[]> generateLegalMoves();
        public void draw(Graphics g) {
            pos.x=ChessPlayer.cToY(col);
            pos.y=ChessPlayer.rToX(row);
            super.draw(g);
        }
    }
