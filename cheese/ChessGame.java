
import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame{
    
    public void playChess(){
        Board b = new Board(); 
        boolean giveUp = false; //idk player can click a button and give up
        while (!giveUp && Board.endGame() == 0){ 
            //CLI stuff
           TestChess.printBoard(b); 
            
            
            Object[] move; 
            Piece moving; 
            int row; 
            int col; 
            if (Board.getWhitesTurn()){ // white's turn 
                move = selectPiece();
                moving = (Piece)move[0]; // piece of where player want to move piece to
                row = (int)move[1]; //row of where piece move to 
                col = (int)move[2]; //col of where piece move to 
            } else {
                moving = getRandomBlackPiece(b); 
                int[] temp = moving.generateRandomMoves(); 
                row = temp[0]; 
                col = temp[1]; 
            }
            moving.move(row, col, false); 
            Board.setWhitesTurn(!Board.getWhitesTurn()); 
        } 
        if (Board.endGame() == 1){ // if white (player) wins
            Player.changeNumCoins(3); 
            System.out.println("You won! You earned 3 coins");
        } else if (Board.endGame() == 2 || giveUp){ // if player lost or give up 
            System.out.println("Tough Luck"); 
        }
        
    }
    
    
     public void playChess(Board b){
        boolean giveUp = false; //idk player can click a button and give up
        while (!giveUp && Board.endGame() == 0){ 
            //CLI stuff
           TestChess.printBoard(b); 
            
            
            Object[] move; 
            Piece moving; 
            int row; 
            int col; 
            if (Board.getWhitesTurn()){ // white's turn 
                move = selectPiece();
                moving = (Piece)move[0]; // piece of where player want to move piece to
                row = (int)move[1]; //row of where piece move to 
                col = (int)move[2]; //col of where piece move to 
            } else {
                moving = getRandomBlackPiece(b); 
                int[] temp = moving.generateRandomMoves(); 
                row = temp[0]; 
                col = temp[1]; 
            }
            moving.move(row, col, false); 
            Board.setWhitesTurn(!Board.getWhitesTurn()); 
        } 
        if (Board.endGame() == 1){ // if white (player) wins
            Player.changeNumCoins(3); 
            System.out.println("You won! You earned 3 coins");
        } else if (Board.endGame() == 2 || giveUp){ // if player lost or give up 
            System.out.println("Tough Luck"); 
        }
        
    }
    
         //return number of black pieces on board that can move 
    public Piece getRandomBlackPiece(Board b){
        ArrayList<Piece> movables = new ArrayList<Piece>(); //all black pieces on board that can move
        
        // populate moveables 
        for(Piece[] r : Board.getBoard()){
            for(Piece p: r){
                if (p!= null && !p.pieceColor && !p.generateLegalMoves().isEmpty()){
                    movables.add(p);  
                }
            }
        }        
        //randomly choose a piece from moveables and return        
        return movables.get((int)(Math.random()*movables.size()));
    }
    
    
    
    //returns a Object [] of 3 objects
    // object[0]: the piece the player want to move
    // object[1]: the row player want to move piece to
    // object[2]: the col player want to move piece to
    public Object[] selectPiece(){
        ///CLI interface, 4 testing
        Scanner in = new Scanner(System.in);
        int r; 
        int c; 
        do{
            System.out.print("See move of piece (row):");
            r = in.nextInt();
            System.out.print("See move of piece (col):");
            c = in.nextInt();
        } while (Board.getBoard()[r][c] == null);
        
        
        
        Object[] ans = new Object [3]; 
        Piece chosen = Board.getBoard()[r][c]; //player click on the piece
         do{
             ArrayList<int[]> legalMoves = chosen.generateLegalMoves();              
             TestChess.printLegalMoves(chosen); 
             for(int[] item : legalMoves){
                 //insert fancy code to color the square on the board a different color
                 
            }
            
            int[] playerSelection = new int[2]; // player select a square on the board
            
            //CLI testing stuff 
            System.out.print("Piece Move (row):");
            playerSelection[0]  = in.nextInt();
            System.out.print("Piece Move (col):");
            playerSelection[1] = in.nextInt();
            System.out.println("you selected: " + playerSelection[0] + ", " + playerSelection[1]);
            
            
            while(true){
                if(containIntArray(legalMoves, playerSelection)){// if player select one of the legal moves
                    ans[0] = chosen; //set ans[0] to the piece chosen
                    ans[1] = playerSelection[0]; // set ans[1] to the row
                    ans[2] = playerSelection[1]; //set ans[2] to the col
                    break; 
                } else if (Board.getBoard()[playerSelection[0]][playerSelection[1]]!= null && onBoard(playerSelection) && Board.getBoard()[playerSelection[0]][playerSelection[1]].pieceColor){ 
                    // else if player selected another piece they own on the board
                    chosen = Board.getBoard()[playerSelection[0]][playerSelection[1]]; // set chosen to the piece they selected and loop again 
                    break; 
                } else{ //player select a square that is not legal move or opponent piece 
                    playerSelection = new int[2]; // player select a square on the board
                    //CLI testing stuff 
                    System.out.print("Piece Move 2(row):");
                    playerSelection[0]  = in.nextInt();
                    System.out.print("Piece Move 2(col):");
                    playerSelection[1] = in.nextInt();
                    System.out.print("you selected" + playerSelection[0] + ", " + playerSelection[1]);
                }
            }
                
        } while (ans[0] == null) ; 
        return ans;          
    }   
    
    //precondition: move is a int array with len of 2
    //move[0] is row and move[1] is col
    //return if move is a possible square on board 
    public boolean onBoard(int[] move){
        return move[0] <= 7 && move[0]>= 0 && move[1] >=0 && move[1] <=7; 
    }
    
    //input: array list of int array and int array
    //returns if container contains target 
    public boolean containIntArray(ArrayList<int[]> container, int[] target){
        for(int[] i : container){
            if(sameIntArray(i, target)){return true; }
        }
        return false; 
    }
    
    //takes 2 int array as input
    //return if the two arrays are the same length and contain the same stuff in the same order. 
    public boolean sameIntArray(int[] a, int[] b){
        if (a.length != b.length) return false; 
        for(int i = 0; i < a.length; i++){
            if (a[i] != b[i]){
                return false; 
            }
        }
        return true; 
    }
}
