import java.util.*;

public abstract class Piece {
        public Piece (int r, int c,boolean color){
            row = r; 
            col = c;
            pieceColor=color;
        }
        protected boolean pieceColor; //true means white
        protected int row;
        protected int col;
        public void move(int r,int c, boolean test) {
            Board.getBoard()[r][c]=this;
            Board.getBoard()[row][col]=null;
            row=r;
            col=c;
        }
         //test if a move will put your king in check,out of bounds, or collides with your own piece
        //true means is a valid move
        protected boolean testMove(int r,int c) {
            if(r<0||r>7||c<0||c>7) return false;
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
            return !a;
        }
        public int[] generateRandomMoves(){
            
            ArrayList<int[]> possibleMoves = generateLegalMoves();
            return possibleMoves.get((int)(Math.random()*possibleMoves.size())); 
        }
        public abstract ArrayList<int[]> generateLegalMoves();
        @Override
        public abstract String toString(); 

    }
