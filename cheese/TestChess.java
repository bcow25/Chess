import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.Image;
import javax.imageio.ImageIO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 800024313
 */
public class TestChess extends JPanel {
    private Image world;

    public static void main(String[] args){
        TestChess y = new TestChess(); 
        
        
        y.initWindow(); 
        testChess(); 

    }
    
    private static void initWindow() {
        // create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Constant W topia");
        // when we close the window, stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the jpanel to draw on.
        // this also initializes the game loop
        ChessBoard.create();
        ChessBoard board = ChessBoard.get();
        // add the jpanel to the window
        window.add(board);
        // pass keyboard inputs to the jpanel
        
        // don't allow the user to resize the window
        window.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }

    
    public static void testPromotion(){
        Board b = new Board();
        for(int i = 0; i <= 7; i++){
            Board.getBoard()[1][i] = null; 
            if(i != 4){
                Board.getBoard()[0][i] = null; 
            }
        }
        ChessGame game = new ChessGame(); 
        game.playChess(b); 
    }
    
    public static void testChess(){
        System.out.print('\u000C');
        ChessGame game = new ChessGame(); 
        game.playChess(); 
    }
    
    public static void testCastle(){
        Board b = new Board(); 
        Piece[][] p = Board.getBoard(); 
        p[0][1] = null;
        p[0][2] = null;
        p[0][3] = null;
        p[1][0] = null; 

                
        printBoard(b);
        printLegalMoves(p[0][4]); 
        printLegalMoves(p[0][0]); 
    }
    
    public static void testQueenInCheck(){
        Board test = new Board();
        Piece[][] p = Board.getBoard(); 
        printBoard(test);
        System.out.println(Board.inCheck());
        Piece temp = p[0][3]; 
        p[0][3] = p[7][3];
        p[0][5] = null; 
        p[7][3] =  temp; 
        p[7][5] = null; 
        printBoard(test); 
        System.out.println(Board.inCheck());

       
    }
    public static void testEnPassantWhite(){
        Board test = new Board(); 
        Board.getBoard()[3][0] = Board.getBoard()[1][0]; 
        Board.getBoard()[1][0] = null; 
        Pawn pB = (Pawn) Board.getBoard()[3][0];  
        pB.row = 3; 
        pB.setCanGetFrenched(true); 
        
        Board.getBoard()[3][2] = Board.getBoard()[1][2]; 
        Board.getBoard()[1][2] = null; 
        Pawn pB1 = (Pawn) Board.getBoard()[3][2];  
        pB1.row = 3; 
        pB1.setCanGetFrenched(true); 
        
        
        Board.getBoard()[3][1] =  Board.getBoard()[6][1]; 
        Board.getBoard()[6][1] = null; 
        printBoard(test); 
        Pawn pW = (Pawn) Board.getBoard()[3][1]; 
        pW.row = 3; 
        printLegalMoves(pW); 
    }
    
    public static void testEnPassantBlack(){
        Board test = new Board(); 
        Board.getBoard()[6][4].move(4, 4, true);
        Pawn pW = (Pawn) Board.getBoard()[4][4];  
        pW.row = 4; 
        pW.setCanGetFrenched(true); 
        
        Board.getBoard()[6][2].move(4, 2,true);
        Pawn pW2 = (Pawn) Board.getBoard()[4][2];  
        pW2.row = 4; 
        pW2.setCanGetFrenched(true); 
        
        
        Board.getBoard()[1][3].move(4, 3,true);
        Pawn pB = (Pawn) Board.getBoard()[4][3];  
        pB.row = 4; 
        pB.setCanGetFrenched(true); 
        
        printBoard(test); 
        printLegalMoves(pB); 
    }
    
    
    public static void printLegalMoves(Piece p){
        System.out.println("Possible Legal Moves of " + p.toString() + ": "); 
        ArrayList <int[]> moves = p.generateLegalMoves(); 
       if (moves.isEmpty()){ System.out.println("no possible moves");}
        for(int[] i : moves){
            System.out.println(i[0] + ", " + i[1]);
        }

    }
    
    
    //tester method for board #1
    public static void printBoard( Board b){
        System.out.println("Row\\Col: 0 \t\t 1\t\t 2\t\t 3\t\t 4\t\t 5\t\t 6\t\t 7");
        for(int r = 0; r < Board.getBoard().length; r++){
            System.out.print(r + "\t");
            for(int c = 0; c < Board.getBoard()[0].length; c++){
                if (Board.getBoard()[r][c] != null)
                    System.out.print(Board.getBoard()[r][c].toString() + "\t");
                else
                    System.out.print("null \t\t");

            
            }
            System.out.println();
        }
    }
    //tester method for board #2
    public static void printAllLegalMoves( Board b){
        for (Piece[] board : Board.getBoard()) {
            for (Piece p : board) {
                if (p != null) {
                    printLegalMoves(p); 
                }
            }
        }       
    }
}
