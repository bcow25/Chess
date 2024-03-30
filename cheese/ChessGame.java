
import java.util.ArrayList;

public class ChessGame{
    
    public void playChess(){
        Board b = new Board(); 
        boolean giveUp = false; //idk player can click a button and give up
        boolean turn = true; //white: true, black: false
        while (!giveUp && Board.endGame() == 0){ 
            Object[] move = selectPiece(); 
            Piece moving = (Piece)move[0]; // player select which piece they want to move
            int row = (int)move[1]; //player choose where to move piece
            int col = (int)move[2]; //player choose where to move piece
            moving.move(row, col); 
        } 
        if (Board.endGame() == 1){ // if white (player) wins
            Player.changeNumCoins(3); 
        } else if (Board.endGame() == 2 || giveUp){ // if player lost or give up 
            System.out.println("Tough Luck"); 
        }
        
    }
    
    //returns a Object [] of 3 objects
    // object[0]: the piece the player want to move
    // object[1]: the row player want to move piece to
    // object[2]: the col player want to move piece to
    public Object[] selectPiece(){
        Object[] ans = new Object [3]; 
         do{
             Piece chosen = Board.getBoard()[0][0]; //player click on the piece
             ArrayList<int[]> legalMoves = chosen.generateLegalMoves(); 
             for(int[] item : legalMoves){
                 //insert fancy code to color the square on the board a different color
            }
            
            if(true){// if player select one of the legal moves
                ans[0] = new Object(); //set ans[0] to the piece chosen
                ans[1] = legalMoves.get(0)[0]; // set ans[1] to the row
                ans[2] = legalMoves.get(0)[1]; //set ans[2] to the col
            }
            //else player didn't choose a legal move and chose to click on another piece, idk im running out of brain juice
            } while (ans[0] == null) ; 
            return ans;          
    }
    
    
    
    //tester method for board #1
    public void printBoard( Board b){
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
