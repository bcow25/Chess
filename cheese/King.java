import java.util.ArrayList;

public class King extends Piece {
       public King(int r, int c,boolean color){
            super(r,c,color); 
        }
       
       @Override
       public String toString (){ return "king:" + pieceColor; }
       @Override
        public void move(int r,int c, boolean test) {
            if(test){
                super.move(r, c, false);
            } else{
                if(c-col==2) Board.getBoard()[r][7].move(r,5, false);
                if(c-col==-2) Board.getBoard()[r][0].move(r, 3, false);
                super.move(r, c, false);
                if(pieceColor) Board.setCastleWhite(false);
                else Board.setCastleBlack(false);
            }
        }
       @Override
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> a=new ArrayList<>();
            for(int r=row-1;r<=row+1;r++) 
                for(int c=col-1;c<=col+1;c++)
                if(testMove(r,c)) a.add(new int[]{r,c});
            boolean c=pieceColor?Board.getCastleWhite():Board.getCastleBlack();
            if(c&&Board.getBoard()[row][col+1]==null&&Board.getBoard()[row][col+2]==null&&testMove(row,col+1)&&testMove(row,col+2)&&!Board.inCheck()) a.add(new int[]{row,col+2});
            if(c&&Board.getBoard()[row][col-1]==null&&Board.getBoard()[row][col-2]==null&&Board.getBoard()[row][col-3]==null&&testMove(row,col-1)&&testMove(row,col-2)&&!Board.inCheck()) a.add(new int[]{row,col-2});
            return a;
        }
    }
