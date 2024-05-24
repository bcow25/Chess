import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
/**
     * ROOK:
     * can move vertically or horizontally as long as it's not blocked by other pieces
     */
    public class Rook extends Piece {
        protected static Image light;
    protected static Image dark;
    protected Image getImage() {
        return pieceColor?light:dark;
    }
        protected void loadImage() {
            try {
                if(dark==null)
                    dark = ImageIO.read(new File("images/chess_pieces/Dark_Rook.png"));
                if(light==null)
                    light = ImageIO.read(new File("images/chess_pieces/Light_Rook.png"));
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //constructor
        public Rook(int r, int c, boolean color){
            super(r, c, color); 
        }
        
        //moves to pieces[r][c]
        public void move(int r, int c, boolean test) {
            if(test){
                super.move(r, c, false);
            } else{
                super.move(r, c, false);
                if (pieceColor)
                    Board.setCastleWhite(false);
                else 
                    Board.setCastleBlack (false);
            }
        }
        public String toString (){  
           if(pieceColor){
                return("White Rook at " + row + ", " + col); 
            } else {return("Black Rook at " + row + ", " + col); }
        }
       public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row;
            while (r >= 1 ) { //checking squares above of current position
                r--;
                if(Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor == pieceColor)break; 
                if(Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor != pieceColor  &&  testMove(r, col)){
                    ans.add(new int[] {r, col} ); 
                    break; 
                }
                if (Board.getBoard()[r][col] == null && testMove(r, col))
                    ans.add(new int[] {r, col} );               
            }
            r = row;
            while (r <= 6 ) { //checking squares below of current position
                r++; 
                if(Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor == pieceColor)break; 
                if(Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor != pieceColor  &&  testMove(r, col)){
                    ans.add(new int[] {r, col} ); 
                    break; 
                }
                if ((Board.getBoard()[r][col] == null || (Board.getBoard()[r][col] != null && Board.getBoard()[r][col].pieceColor != pieceColor ) ) && testMove(r,col))
                    ans.add(new int[] {r, col} );
            }
            int c = col;
            while (c >= 1) { //checking squares left current position
                c--; 
                if(Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor == pieceColor)break; 
                if(Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor != pieceColor  &&  testMove(row, c)){
                    ans.add(new int[] {row, c} ); 
                    break; 
                }
                if ((Board.getBoard()[row][c] == null || (Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor != pieceColor )) && testMove(row,c)) 
                    ans.add(new int[] {row, c} );
            }
            c = col;
            while (c <= 6 ) { //checking squares right current position
                c++; 
                if(Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor == pieceColor)break; 
                if(Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor != pieceColor  &&  testMove(row, c)){
                    ans.add(new int[] {row, c} ); 
                    break; 
                }
                if ((Board.getBoard()[row][c] == null || (Board.getBoard()[row][c] != null && Board.getBoard()[row][c].pieceColor != pieceColor ))){
                    if(testMove(row,c)) 
                        ans.add(new int[] {row, c} );
                }
                
            }
            return ans;
        }
    }
