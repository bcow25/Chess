import java.util.ArrayList;

public class King extends Piece {
       public King(int r, int c,boolean color){
            super(r,c,color); 
        }
       
       public String toString (){ return "king:" + pieceColor; }
        public void move(int r,int c) {
            if(c-col==2) Board.pieces[r][7].move(r,5);
            if(c-col==-2) Board.pieces[r][0].move(r, 3);
            super.move(r, c);
            if(pieceColor) Board.castleWhite=false;
            else Board.castleBlack=false;
        }
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> a=new ArrayList<>();
            for(int r=row-1;r<=row+1;r++) 
                for(int c=col-1;c<=col+1;c++)
                if(testMove(r,c)) a.add(new int[]{r,c});
            boolean c=pieceColor?Board.castleWhite:Board.castleBlack;
            if(c&&Board.pieces[row][col+1]==null&&Board.pieces[row][col+2]==null&&testMove(row,col+1)&&testMove(row,col+2)&&!Board.inCheck()) a.add(new int[]{row,col+2});
            if(c&&Board.pieces[row][col-1]==null&&Board.pieces[row][col-2]==null&&Board.pieces[row][col-3]==null&&testMove(row,col-1)&&testMove(row,col-2)&&!Board.inCheck()) a.add(new int[]{row,col-2});
            return a;
        }
    }