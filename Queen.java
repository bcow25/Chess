import java.util.ArrayList;

public class Queen extends Piece { //dont question the code idk how to comment just trust 
        public Queen(int r, int c,boolean color){
            super(r,c,color); 
        }
        
        public String toString(){ return "queen:" + pieceColor; }
        
        public void move(int r,int c) {
            
            //if queen is capturing
            if((r + 2 == row || r - 2 == row || r == row) && (c + 2 == col || c - 2 == col || c == col)){
                if(r == row){
                    if (c + 2== col && c > 0 && Board.pieces[r][c-1] != null && Board.pieces[r][c-1].pieceColor != pieceColor){
                        Board.pieces[r][c-1] = null; 
                    } else if (c < 7 && Board.pieces[r][c+1] != null && Board.pieces[r][c+1].pieceColor != pieceColor){
                        Board.pieces[r][c+1] = null; 
                    }
                } else if (c == col){
                    if( r + 2 == row && r > 0 && Board.pieces[r-1][c] != null && Board.pieces[r-1][c].pieceColor != pieceColor){
                        Board.pieces[r-1][c] = null; 
                    } else if(r < 7 && Board.pieces[r+1][c] != null && Board.pieces[r+1][c].pieceColor != pieceColor){
                        Board.pieces[r+1][c] = null; 
                    }
                } else if (r + 2 == row && c + 2 == col && r > 0 && c > 0 && Board.pieces[r-1][c-1] != null && Board.pieces[r-1][c-1].pieceColor != pieceColor){
                    Board.pieces[r-1][c-1] = null; 
                } else if (r - 2 == row && c + 2 == col && r < 7 && c > 0 && Board.pieces[r+1][c-1] != null && Board.pieces[r+1][c-1].pieceColor != pieceColor){
                    Board.pieces[r+1][c-1] = null; 
                } else if (r + 2 == row && c - 2 == col && r > 0 && c < 7 && Board.pieces[r-1][c+1] != null && Board.pieces[r-1][c+1].pieceColor != pieceColor){
                    Board.pieces[r-1][c+1] = null; 
                } else if(r - 2 == row && c - 2 == col && r < 7 && c < 7 && Board.pieces[r+1][c+1] != null && Board.pieces[r+1][c+1].pieceColor != pieceColor){
                    Board.pieces[r+1][c+1] = null; 
                }
            }
            Board.pieces[r][c]=this;
            Board.pieces[row][col]=null;
            row=r;
            col=c;
        }
        
        // returns an arrayList of possible capture moves to male generate legal move more readable 
        // see how queen capture in README
         public ArrayList<int[]> generateCaptureMoves() {
            ArrayList <int[]> ans = new ArrayList<>(); 
            if(row > 1){
                if (Board.pieces[row-1][col] != null && Board.pieces[row-1][col].pieceColor != pieceColor && Board.pieces[row-2][col] == null && testMove(row-2, col)) ans.add(new int[]{row-2, col}); 
                if(col > 1 && Board.pieces[row-1][col-1] != null && Board.pieces[row-1][col-1].pieceColor != pieceColor && Board.pieces[row-2][col-2] == null && testMove(row-2, col-2)) ans.add(new int[]{row-2, col-2}); 
                if(col < 6 && Board.pieces[row-1][col+1] != null && Board.pieces[row-1][col+1].pieceColor != pieceColor && Board.pieces[row-2][col+2] == null && testMove(row-2, col+2)) ans.add(new int[]{row-2, col+2}); 
            }
            
            if(row < 6){
                if (Board.pieces[row+1][col] != null && Board.pieces[row+1][col].pieceColor != pieceColor && Board.pieces[row+2][col] == null && testMove(row+2, col)) ans.add(new int[]{row+2, col}); 
                if(col > 1 && Board.pieces[row+1][col-1] != null && Board.pieces[row+1][col-1].pieceColor != pieceColor && Board.pieces[row+2][col-2] == null && testMove(row+2, col-2)) ans.add(new int[]{row+2, col-2}); 
                if(col < 6 && Board.pieces[row+1 ][col+1] != null && Board.pieces[row+1 ][col+1].pieceColor != pieceColor && Board.pieces[row+2][col+2] == null && testMove(row+1, col+1)) ans.add(new int[]{row+2, col+2}); 
            }
            
            if (col != 0 && Board.pieces[row][col-1] != null && Board.pieces[row][col-1].pieceColor != pieceColor && Board.pieces[row][col-2] == null && testMove(row, col-2)) ans.add(new int[]{row, col-2}); 
            if (col != 7 && Board.pieces[row][col+1] != null && Board.pieces[row][col+1].pieceColor != pieceColor && Board.pieces[row][col + 2] == null && testMove(row, col+1)) ans.add(new int[]{row, col+2}); 
            
            return ans;
        }
        
        public ArrayList<int[]> generateLegalMoves() {
            boolean canMove = false; 
            ArrayList <int[]> ans = generateCaptureMoves(); //add list of capture moves to ans 
            //System.out.print(ans.size());
            for(int r = row+1; r <= 7; r ++){
                if(Board.pieces[r][col] != null && Board.pieces[r][col].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && Board.pieces[r][col] == null && testMove(r, col)) ans.add(new int[]{r, col});                     
            }
            canMove = false; 
            for(int r = row-1; r >= 0; r --){
                if(Board.pieces[r][col] != null && Board.pieces[r][col].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && Board.pieces[r][col] == null && testMove(r, col)) ans.add(new int[]{r, col});                     
            }
            
             canMove = false; 
            for(int c = col-1; c >= 0; c --){
                if(Board.pieces[row][c] != null && Board.pieces[row][c].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && Board.pieces[row][c] == null && testMove(row, c)) ans.add(new int[]{row, c});                     
            }
            canMove = false; 
            for(int c = col+1; c <= 7; c ++){
                if(Board.pieces[row][c] != null && Board.pieces[row][c].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && Board.pieces[row][c] == null && testMove(row, c)) ans.add(new int[]{row, c});                     
            }
            canMove = false; 
            int r = row; 
            int c = col; 
           while( r >= 1 && c >= 1){
               r--; 
               c--; 
               if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && Board.pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           r = row; 
           c = col; 
           canMove = false; 
           while( r <= 6 && c >= 1){
               r++; 
               c--; 
               if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && Board.pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           
           canMove = false; 
           r = row; 
           c = col; 
           while( r <= 6 && c <= 6){
               r++; 
               c++; 
               if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && Board.pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           canMove = false; 
           r = row; 
           c = col; 
           while( r >= 1 && c <= 6){
               r--; 
               c++; 
               if(Board.pieces[r][c] != null && Board.pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && Board.pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           return ans; 
        }
    }