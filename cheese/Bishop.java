import java.util.ArrayList;

/**
     * BISHOP:
     * moves diagonally as long as it's not blocked by its own pieces
     */
   
    public class Bishop extends Piece {
        public Bishop(int r, int c,boolean color){
            super(r,c,color); 
        }
        public String toString (){return "bishop:" + pieceColor;}
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row; 
            int c = col; 
            
            while(r <= 6 && c <= 6){
                r++; 
                c++; 
                if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor)break; 
                if (Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if(Board.pieces[r][c] == null && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
                
            }
            r = row; 
            c = col; 
            while(r >= 1 && c <= 6 ){
                r--; 
                c++; 
                if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor)break; 
                if (Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if((Board.pieces[r][c] == null || Board.pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
            }
            r = row; 
            c = col; 
            while(r >= 1 && c >= 1){
                r--; 
                c--; 
                if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor)break; 
                if (Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if((Board.pieces[r][c] == null || Board.pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
                
            }
            r = row; 
            c = col; 
            while(r <= 6 && c >= 1){
                r++; 
                c--; 
                if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor)break; 
                if (Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if((Board.pieces[r][c] == null || Board.pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c});
                } 
            }
            return ans;
        }
    }
