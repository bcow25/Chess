import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
public class Knight extends Piece {

        protected void loadImage() {
            try {
    
                killme = ImageIO.read(new File("images/player.jpg")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
                while (killme == null); //most sane code written by tracy
                } catch (IOException exc) {
                System.out.println("Error opening image file: " + exc.getMessage());
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
