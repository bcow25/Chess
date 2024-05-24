import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
public class Knight extends Piece {
    protected static Image light;
    protected static Image dark;
    protected Image getImage() {
        return pieceColor?light:dark;
    }
        protected void loadImage() {
            try {
                if(dark==null)
                    dark = ImageIO.read(new File("images/chess_pieces/Dark_Knight.png"));
                if(light==null)
                    light = ImageIO.read(new File("images/chess_pieces/Light_Knight.png"));
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
      //constructor
      public Knight(int r, int c,boolean color){
          super(r,c,color); 
      }
      
      @Override
      public String toString (){  
           if(pieceColor){
                return("White Knight at " + row + ", " + col); 
            } else {return("Black Knight at " + row + ", " + col); }
        }
        
      @Override
      public ArrayList<int[]> generateLegalMoves() {
          ArrayList<int[]> ans = new ArrayList<int[]>();
          if (row - 2 >= 0 && col - 1 >= 0)
                if (Board.getBoard()[row - 2][col - 1] == null || (Board.getBoard()[row - 2][col - 1] != null && Board.getBoard()[row - 2][col - 1].pieceColor != Board.getBoard()[row][col].pieceColor)) //2 up, 1 left
                    if(testMove(row - 2,col - 1))
                        ans.add(new int[]{row - 2, col - 1});
          if (row - 1 >= 0 && col - 2 >= 0)
                if (Board.getBoard()[row - 1][col - 2] == null || (Board.getBoard()[row - 1][col - 2] == null && Board.getBoard()[row - 1][col - 2].pieceColor != Board.getBoard()[row][col].pieceColor)) // 1 up, 2 left
                    if(testMove(row - 1,col - 2))
                        ans.add(new int[]{row - 1, col - 2});
          if (row + 1 <= 7 && col - 2 >= 0)
                if (Board.getBoard()[row + 1][col - 2] == null || (Board.getBoard()[row + 1][col - 2] == null && Board.getBoard()[row + 1][col - 2].pieceColor != Board.getBoard()[row][col].pieceColor)) // 1 down, 2 left
                    if(testMove(row + 1,col - 2))
                        ans.add(new int[]{row + 1, col - 2});
          if (row + 2 <= 7 && col - 1 >= 0)
                if (Board.getBoard()[row + 2][col - 1] == null || (Board.getBoard()[row + 2][col - 1] == null && Board.getBoard()[row + 2][col - 1].pieceColor != Board.getBoard()[row][col].pieceColor)) // 2 down, 1 left
                    if(testMove(row + 2,col - 1))
                        ans.add(new int[]{row + 2, col - 1});
          if (row - 2 >= 0 && col + 1 <= 7)
                if (Board.getBoard()[row - 2][col + 1] == null || (Board.getBoard()[row - 2][col + 1] == null && Board.getBoard()[row - 2][col + 1].pieceColor != Board.getBoard()[row][col].pieceColor)) // 2 up, 1 right
                    if(testMove(row - 2,col + 1))
                        ans.add(new int[]{row - 2, col + 1});
          if (row - 1 >= 0 && col + 2 <= 7)
                if (Board.getBoard()[row - 1][col + 2] == null || (Board.getBoard()[row - 1][col + 2] == null && Board.getBoard()[row - 1][col + 2].pieceColor != Board.getBoard()[row][col].pieceColor)) // 1 up, 2 right
                    if(testMove(row - 1,col + 2))
                        ans.add(new int[]{row - 1, col + 2});
          if (row + 1 <= 7 && col + 2 <= 7)
                if (Board.getBoard()[row + 1][col + 2] == null || (Board.getBoard()[row + 1][col + 2] == null && Board.getBoard()[row + 1][col + 2].pieceColor != Board.getBoard()[row][col].pieceColor)) // 1 down, 2 right
                    if(testMove(row + 1,col + 2))
                        ans.add(new int[]{row + 1, col + 2});
          if (row + 2 <= 7 && col + 1 <= 7)
                if (Board.getBoard()[row + 2][col + 1] == null || (Board.getBoard()[row + 2][col + 1] == null && Board.getBoard()[row + 2][col + 1].pieceColor != Board.getBoard()[row][col].pieceColor)) // 2 down, 1 right
                    if(testMove(row + 2,col + 1)) 
                        ans.add(new int[]{row + 2, col + 1});
            return ans;
        }
    }
