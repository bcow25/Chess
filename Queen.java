import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Queen extends Piece { //dont question the code idk how to comment just trust 
    protected static Image light;
    protected static Image dark;
    protected Image getImage() {
        return pieceColor?light:dark;
    }
        protected void loadImage() {
            try {
                if(dark==null)
                    dark = ImageIO.read(new File("images/chess_pieces/Dark_Queen.png"));
                if(light==null)
                    light = ImageIO.read(new File("images/chess_pieces/Light_Queen.png"));
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public Queen(int r, int c,boolean color){
            super(r,c,color); 
            
        }
        
        @Override
        public String toString(){ 
            if(pieceColor){
                return("White Queen at " + row + ", " + col); 
            } else {return("Black Queen at " + row + ", " + col); }
        }
        
        @Override
        public void move(int r,int c, boolean test) {
            
            
            if(test){
                super.move(r, c, false);
            } else{
            
                //if queen is capturing
                if((r + 2 == row || r - 2 == row || r == row) && (c + 2 == col || c - 2 == col || c == col)){
                    if(r == row){
                        if (c + 2== col && c > 0 && Board.getBoard()[r][c-1] != null && Board.getBoard()[r][c-1].pieceColor != pieceColor){
                            Board.getBoard()[r][c-1] = null; 
                        } else if (c < 7 && Board.getBoard()[r][c+1] != null && Board.getBoard()[r][c+1].pieceColor != pieceColor){
                            Board.getBoard()[r][c+1] = null; 
                        }
                    } else if (c == col){
                        if( r + 2 == row && r > 0 && Board.getBoard()[r-1][c] != null && Board.getBoard()[r-1][c].pieceColor != pieceColor){
                            Board.getBoard()[r-1][c] = null; 
                        } else if(r < 7 && Board.getBoard()[r+1][c] != null && Board.getBoard()[r+1][c].pieceColor != pieceColor){
                            Board.getBoard()[r+1][c] = null; 
                        }
                    } else if (r + 2 == row && c + 2 == col && r > 0 && c > 0 && Board.getBoard()[r-1][c-1] != null && Board.getBoard()[r-1][c-1].pieceColor != pieceColor){
                        Board.getBoard()[r-1][c-1] = null; 
                    } else if (r - 2 == row && c + 2 == col && r < 7 && c > 0 && Board.getBoard()[r+1][c-1] != null && Board.getBoard()[r+1][c-1].pieceColor != pieceColor){
                        Board.getBoard()[r+1][c-1] = null; 
                    } else if (r + 2 == row && c - 2 == col && r > 0 && c < 7 && Board.getBoard()[r-1][c+1] != null && Board.getBoard()[r-1][c+1].pieceColor != pieceColor){
                        Board.getBoard()[r-1][c+1] = null; 
                    } else if(r - 2 == row && c - 2 == col && r < 7 && c < 7 && Board.getBoard()[r+1][c+1] != null && Board.getBoard()[r+1][c+1].pieceColor != pieceColor){
                        Board.getBoard()[r+1][c+1] = null; 
                    }
                }
                Board.getBoard()[r][c]=this;
                Board.getBoard()[row][col]=null;
                if(row < r){
                    for(int i = row; i < r; i++){
                        pos.y += Board.squareSize; 
                    }
                } else{
                    for(int i = r; i < row; i++){
                        pos.y -= Board.squareSize; 
                    }
                }
                if(col < c){
                    for(int i = col; i < c; i++){
                        pos.x += Board.squareSize; 
                    }
                } else{
                    for(int i = c; i < col; i++){
                        pos.x -= Board.squareSize; 
                    }
                }
                row=r;
                col=c;

            }
        }
        
        // returns an arrayList of possible capture moves to male generate legal move more readable 
        // see how queen capture in README
         public ArrayList<int[]> generateCaptureMoves() {
            ArrayList <int[]> ans = new ArrayList<>(); 
            if(row > 1){
                if (Board.getBoard()[row-1][col] != null && Board.getBoard()[row-1][col].pieceColor != pieceColor && Board.getBoard()[row-2][col] == null && testMove(row-2, col)) ans.add(new int[]{row-2, col}); 
                if(col > 1 && Board.getBoard()[row-1][col-1] != null && Board.getBoard()[row-1][col-1].pieceColor != pieceColor && Board.getBoard()[row-2][col-2] == null && testMove(row-2, col-2)) ans.add(new int[]{row-2, col-2}); 
                if(col < 6 && Board.getBoard()[row-1][col+1] != null && Board.getBoard()[row-1][col+1].pieceColor != pieceColor && Board.getBoard()[row-2][col+2] == null && testMove(row-2, col+2)) ans.add(new int[]{row-2, col+2}); 
            }
            
            if(row < 6){
                if (Board.getBoard()[row+1][col] != null && Board.getBoard()[row+1][col].pieceColor != pieceColor && Board.getBoard()[row+2][col] == null && testMove(row+2, col)) ans.add(new int[]{row+2, col}); 
                if(col > 1 && Board.getBoard()[row+1][col-1] != null && Board.getBoard()[row+1][col-1].pieceColor != pieceColor && Board.getBoard()[row+2][col-2] == null && testMove(row+2, col-2)) ans.add(new int[]{row+2, col-2}); 
                if(col < 6 && Board.getBoard()[row+1 ][col+1] != null && Board.getBoard()[row+1 ][col+1].pieceColor != pieceColor && Board.getBoard()[row+2][col+2] == null && testMove(row+1, col+1)) ans.add(new int[]{row+2, col+2}); 
            }
            
            if (col != 0 && Board.getBoard()[row][col-1] != null && Board.getBoard()[row][col-1].pieceColor != pieceColor && Board.getBoard()[row][col-2] == null && testMove(row, col-2)) ans.add(new int[]{row, col-2}); 
            if (col != 7 && Board.getBoard()[row][col+1] != null && Board.getBoard()[row][col+1].pieceColor != pieceColor && Board.getBoard()[row][col + 2] == null && testMove(row, col+1)) ans.add(new int[]{row, col+2}); 
            
            return ans;
        }
        
        @Override
        public ArrayList<int[]> generateLegalMoves() {
            boolean canMove = false; 
            ArrayList <int[]> ans = generateCaptureMoves(); //add list of capture moves to ans 
            //System.out.print(ans.size());
            for(int r = row+1; r <= 7; r ++){
                if(Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if (Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor != pieceColor) break; 
                if(canMove && Board.getBoard()[r][col] == null && testMove(r, col)) ans.add(new int[]{r, col});                     
            }
            canMove = false; 
            for(int r = row-1; r >= 0; r --){
                if(Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if (Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor != pieceColor) break; 
                if(canMove && Board.getBoard()[r][col] == null && testMove(r, col)) ans.add(new int[]{r, col});                     
            }
            
             canMove = false; 
            for(int c = col-1; c >= 0; c --){
                if(Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if (Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor != pieceColor) break; 
                if(canMove && Board.getBoard()[row][c] == null && testMove(row, c)) ans.add(new int[]{row, c});                     
            }
            canMove = false; 
            for(int c = col+1; c <= 7; c ++){
                if(Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if (Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor != pieceColor) break; 
                if(canMove && Board.getBoard()[row][c] == null && testMove(row, c)) ans.add(new int[]{row, c});                     
            }
            canMove = false; 
            int r = row; 
            int c = col; 
           while( r >= 1 && c >= 1){
               r--; 
               c--; 
               if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor) canMove = true; 
               if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor) break; 
               if(canMove && Board.getBoard()[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           r = row; 
           c = col; 
           canMove = false; 
           while( r <= 6 && c >= 1){
               r++; 
               c--; 
               if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor) canMove = true; 
               if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor) break; 
               if(canMove && Board.getBoard()[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           
           canMove = false; 
           r = row; 
           c = col; 
           while( r <= 6 && c <= 6){
               r++; 
               c++; 
               if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor) canMove = true; 
               if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor) break; 
               if(canMove && Board.getBoard()[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           canMove = false; 
           r = row; 
           c = col; 
           while( r >= 1 && c <= 6){
               r--; 
               c++; 
               if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor) canMove = true; 
               if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor) break; 
               if(canMove && Board.getBoard()[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           return ans; 
        }
    }
