
import java.util.ArrayList;

public class Driver{
    
    public static void main(String[] args){
    //Tests go here 
    Board test = new Board();
    printBoard(test);
    
    System.out.println();
    
    printAllLegalMoves(test); 
  }  
    
    public static void printBoard( Board b){
        System.out.println("Row\\Col: 0 \t\t 1\t\t 2\t\t 3\t\t 4\t\t 5\t\t 6\t\t 7");
        for(int r = 0; r < b.getBoard().length; r++){
            System.out.print(r + "\t");
            for(int c = 0; c < b.getBoard()[0].length; c++){
                if (b.getBoard()[r][c] != null)
                    System.out.print(b.getBoard()[r][c].getName() + "\t");
                else
                    System.out.print("null \t\t");

            
            }
            System.out.println();
        }
    }
    
    public static void printAllLegalMoves( Board b){
            for(int r = 0; r < b.getBoard().length; r++){
                for(int c = 0; c < b.getBoard()[0].length; c++){
                    if (b.getBoard()[r][c] != null){
                        System.out.print(b.getBoard()[r][c].getName() + ": ");
                        ArrayList<int[]> moves = b.getBoard()[r][c].generateLegalMoves(); 
                        for(int[] move : moves){
                            System.out.print("[R: " + move[0] + ", C:" + move[1] + "]");
                        }
                        System.out.println(moves.size());
                    }

                }
            }
        
    }
}
