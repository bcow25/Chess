
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 800024313
 */
public class TestChess {

    public static void main(String[] args){
        testEnPassantBlack(); 

    }
    public static void testEnPassantWhite(){
        Board test = new Board(); 
        test.getBoard()[3][0] = test.getBoard()[1][0]; 
        test.getBoard()[1][0] = null; 
        Pawn pB = (Pawn) test.getBoard()[3][0];  
        pB.row = 3; 
        pB.setCanGetFrenched(true); 
        
        test.getBoard()[3][2] = test.getBoard()[1][2]; 
        test.getBoard()[1][2] = null; 
        Pawn pB1 = (Pawn) test.getBoard()[3][2];  
        pB1.row = 3; 
        pB1.setCanGetFrenched(true); 
        
        
        test.getBoard()[3][1] =  test.getBoard()[6][1]; 
        test.getBoard()[6][1] = null; 
        printBoard(test); 
        Pawn pW = (Pawn) test.getBoard()[3][1]; 
        pW.row = 3; 
        printLegalMoves(pW); 
    }
    
    public static void testEnPassantBlack(){
        Board test = new Board(); 
        test.getBoard()[6][4].move(4, 4);
        Pawn pW = (Pawn) test.getBoard()[4][4];  
        pW.row = 4; 
        pW.setCanGetFrenched(true); 
        
        test.getBoard()[6][2].move(4, 2);
        Pawn pW2 = (Pawn) test.getBoard()[4][2];  
        pW2.row = 4; 
        pW2.setCanGetFrenched(true); 
        
        
        test.getBoard()[1][3].move(4, 3);
        Pawn pB = (Pawn) test.getBoard()[4][3];  
        pB.row = 4; 
        pB.setCanGetFrenched(true); 
        
        printBoard(test); 
        printLegalMoves(pB); 
    }
    
    
    public static void printLegalMoves(Piece p){
        System.out.println(p.toString()); 
        ArrayList <int[]> moves = p.generateLegalMoves(); 
        for(int[] i : moves){
            System.out.println(i[0] + ", " + i[1]);
        }
    }
    
    
    //tester method for board #1
    public static void printBoard( Board b){
        System.out.println("Row\\Col: 0 \t\t 1\t\t 2\t\t 3\t\t 4\t\t 5\t\t 6\t\t 7");
        for(int r = 0; r < b.getBoard().length; r++){
            System.out.print(r + "\t");
            for(int c = 0; c < b.getBoard()[0].length; c++){
                if (b.getBoard()[r][c] != null)
                    System.out.print(b.getBoard()[r][c].toString() + "\t");
                else
                    System.out.print("null \t\t");

            
            }
            System.out.println();
        }
    }
    //tester method for board #2
    public void printAllLegalMoves( Board b){
            for(int r = 0; r < b.getBoard().length; r++){
                for(int c = 0; c < b.getBoard()[0].length; c++){
                    if (b.getBoard()[r][c] != null){
                        System.out.print(b.getBoard()[r][c].toString() + ": ");
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
