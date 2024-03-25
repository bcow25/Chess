import java.util.ArrayList;

/**
     * ROOK:
     * can move vertically or horizontally as long as it's not blocked by other pieces
     */
    public class Rook extends Piece {
        //constructor
        public Rook(int r, int c, boolean color){
            super(r, c, color); 
        }
        
        //moves to pieces[r][c]
        public void move(int r, int c) {
            super.move(r, c);
            if (pieceColor)
                Board.castleWhite = false;
            else 
                Board.castleBlack = false;
        }
        public String toString (){ return "rook: " + pieceColor;}
       public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row;
            while (r >= 1 ) { //checking squares left of current position
                r--;
                if(Board.pieces[r][col] != null && Board.pieces[r][col].pieceColor == pieceColor)break; 
                if ((Board.pieces[r][col] == null || (Board.pieces[r][col] != null && Board.pieces[r][col].pieceColor != pieceColor )) && testMove(r, col))
                    ans.add(new int[] {r, col} );               
            }
            r = row;
            while (r <= 6 ) { //checking squares right of current position
                r++; 
                if(Board.pieces[r][col] != null && Board.pieces[r][col].pieceColor == pieceColor)break; 
                if ((Board.pieces[r][col] == null || (Board.pieces[r][col] != null && Board.pieces[r][col].pieceColor != pieceColor ) ) && testMove(r,col))
                    ans.add(new int[] {r, col} );
            }
            int c = col;
            while (c >= 1) { //checking squares above current position
                c--; 
                if(Board.pieces[row][c] != null && Board.pieces[row][c].pieceColor == pieceColor)break; 
                if ((Board.pieces[row][c] == null || (Board.pieces[row][c] != null && Board.pieces[row][c].pieceColor != pieceColor )) && testMove(row,col)) 
                    ans.add(new int[] {row, c} );
            }
            c = col;
            while (c <= 6 ) { //checking squares below current position
                c++; 
                if(Board.pieces[row][c] != null && Board.pieces[row][c].pieceColor == pieceColor)break; 
                if ((Board.pieces[row][c] == null || (Board.pieces[row][c] != null && Board.pieces[row][c].pieceColor != pieceColor )) && testMove(row,col)) 
                    ans.add(new int[] {row, c} );
            }
            return ans;
        }
    }