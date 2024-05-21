import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Point;

/**
     * BISHOP:
     * moves diagonally as long as it's not blocked by its own pieces
     */
   
    public class Bishop extends Piece {

        protected void loadImage() {
            try {
                if(pieceColor){
                    killme = ImageIO.read(new File("images/chess_pieces/Dark_Bishop.png")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
                } else {
                    killme = ImageIO.read(new File("images/chess_pieces/Light_Bishop.png")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
                }
                
                while (killme == null); //most sane code written by tracy
                } catch (IOException exc) {
                System.out.println("Error opening image file: " + exc.getMessage());
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
