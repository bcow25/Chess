
import java.util.ArrayList;

public class Driver{
    
    public static void printBoard( Board b){
        for(int r = 0; r < b.getBoard().length; r++){
            for(int c = 0; c < b.getBoard()[0].length; c++){
                if (b.getBoard()[r][c] != null)
                    System.out.print(b.getBoard()[r][c].getName() + "\t");
                else
                    System.out.print("null \t");

            
            }
            System.out.println();
        }
    }
    

    
    public static void printAllLegalMoves( Board b){
            for(int r = 0; r < b.getBoard().length; r++){
                for(int c = 0; c < b.getBoard()[0].length; c++){
                    if (b.getBoard()[r][c] != null){
                        
                        System.out.println(); 
                    }

                }
                System.out.println();
            }
        
    }
    
    
  public static void main(String[] args){
    //Tests go here 
    Board test = new Board();
    printBoard(test);
    
    System.out.println();
    
    printAllLegalMoves(test); 
  }

}
