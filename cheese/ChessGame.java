
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit; 

public class ChessGame{
    private boolean giveUp = false; //idk player can click a button and give up    
    
     public void playChess(Board b){
        while (!giveUp && Board.endGame() == 0){ 
            Object[] move; 
            Piece moving; 
            int row; 
            int col; 
            if (Board.getWhitesTurn()){ // white's turn 
                move = selectPiece();
                moving = (Piece)move[0]; // piece of where player want to move piece to
                if(move[1] == null){
                    break; 
                }
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
        pause(1); 
        if (Board.endGame() == 1){ // if white (player) wins
            System.out.print('\u000C');
            int ind = (int)(Math.random()* 10);
           // Game.buy(ind); 
            System.out.println("You won! You got "/*  + Game.getPlant(ind).getName()*/);
            pause(2); 
            TestChess.closeWindow(); 
            System.out.print('\u000C');
        } else if (Board.endGame() == 2 || giveUp){ // if player lost or give up 
            System.out.print('\u000C');
            System.out.println("Skill issue do better" ); 
            pause(2); 
            TestChess.closeWindow(); 
            for(int i = 0; i < 30; i ++){
                System.out.println("Skill issue do better" ); 
            }
            System.out.print('\u000C');
            System.out.println("LL\nLL\nLL\nLL\nLL\nLL\nLL\nLL\nLL\nLL\nLL\nLL\nLLLLLLLLLLLLLLLLLLLLL");
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
    
    //pause terminal for i secs
    public void pause(double i){
        try{
                TimeUnit.SECONDS.sleep((long)i);
            } catch(Exception exception) {}
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
        System.out.print("Enter -1 to take the L\nSee move of piece (row): ");
        r = in.nextInt();
        if (r == -1){
            giveUp = true; 
            return new Object[3]; 
        }
        System.out.print("See move of piece (col): ");
        c = in.nextInt();
        pause(1); 
        while ((r > 7 || r < 0 || c > 7 || c < 0) || Board.getBoard()[r][c] == null || Board.getBoard()[r][c].pieceColor != Board.getWhitesTurn()) {
            System.out.print('\u000C');
            if (r > 7 || r < 0 || c > 7 || c < 0){
                System.out.println("There are the rows and columns goes from 0 to 7, choose a new location on the board"); 
            } else if ( Board.getBoard()[r][c] != null && Board.getBoard()[r][c].pieceColor != Board.getWhitesTurn()){
                System.out.println("You can only move pieces of your own color. Choose another piece."); 
            } else {
                System.out.println("The location you chose is empty. Please choose a new location."); 
            }
            
            
            pause(1); 
            System.out.print("See move of piece (row): ");
            r = in.nextInt();
            System.out.print("See move of piece (col): ");
            c = in.nextInt();
            
            pause(1); 
        }
        System.out.print('\u000C');
        
        
        Object[] ans = new Object [3]; 
        Piece chosen = Board.getBoard()[r][c]; //player click on the piece
         do{
             ArrayList<int[]> legalMoves = chosen.generateLegalMoves();              
             TestChess.printLegalMoves(chosen); 
            
            int[] playerSelection = new int[2]; // player select a square on the board
            
            //CLI testing stuff 
            System.out.println("Select where you want to move your piece to. If you want to move another piece of your own color, enter its location here ");
             System.out.print("Enter -1 to take the L\nPiece Move (row): ");
                playerSelection[0]  = in.nextInt();
                if (playerSelection[0] == -1){
                    giveUp = true; 
                    return new Object[3]; 
                }
                System.out.print("Piece Move (col): ");
                playerSelection[1] = in.nextInt();
                System.out.println("you selected: " + playerSelection[0] + ", " + playerSelection[1]);
                pause(1); 
                System.out.print('\u000C');
            
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
                    System.out.print('\u000C');
                    System.out.println("You did not choose a valid move. Choose again. ");
                    TestChess.printLegalMoves(chosen); 
                    System.out.print("Enter -1 to take the L\nPiece Move (row): ");
                    playerSelection[0]  = in.nextInt();
                    if (playerSelection[0] == -1){
                        giveUp = true;
                        return new Object [3]; 
                    }
                    System.out.print("Piece Move (col): ");
                    playerSelection[1] = in.nextInt();
                    System.out.print("you selected " + playerSelection[0] + ", " + playerSelection[1]);
                    pause(1); 
                    System.out.print('\u000C');
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
