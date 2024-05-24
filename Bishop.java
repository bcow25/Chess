import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
     * BISHOP:
     * moves diagonally as long as it's not blocked by its own pieces
     */
   
    public class Bishop extends Piece {
        protected static Image light;
        protected Image getImage() {
            return pieceColor?light:dark;
        }
    protected static Image dark;
        protected void loadImage() {
            try {
                if(dark==null)
                    dark = ImageIO.read(new File("images/chess_pieces/Dark_Bishop.png"));
                if(light==null)
                    light = ImageIO.read(new File("images/chess_pieces/Light_Bishop.png"));
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public Bishop(int r, int c,boolean color){
            super(r,c,color);
             
        }
        @Override
        public String toString (){  
           if(pieceColor){
                return("White Bishop at " + row + ", " + col); 
            } else {return("Black Bishop at " + row + ", " + col); }
        }
        @Override
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row; 
            int c = col; 
            
            while(r <= 6 && c <= 6){
                r++; 
                c++; 
                if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor)break; 
                if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if(Board.getBoard()[r][c] == null && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
                
            }
            r = row; 
            c = col; 
            while(r >= 1 && c <= 6 ){
                r--; 
                c++; 
                if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor)break; 
                if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if((Board.getBoard()[r][c] == null || Board.getBoard()[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
            }
            r = row; 
            c = col; 
            while(r >= 1 && c >= 1){
                r--; 
                c--; 
                if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor)break; 
                if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if((Board.getBoard()[r][c] == null || Board.getBoard()[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
                
            }
            r = row; 
            c = col; 
            while(r <= 6 && c >= 1){
                r++; 
                c--; 
                if(Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor == pieceColor)break; 
                if (Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != pieceColor && testMove(r, c)){
                    ans.add(new int[]{r,c});
                    break; 
                }
                if((Board.getBoard()[r][c] == null || Board.getBoard()[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c});
                } 
            }
            return ans;
        }
    }
