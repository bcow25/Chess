
import java.util.ArrayList;

public class ChessGame{
    
    public void playChess(){
        Board b = new Board(); 
        boolean giveUp = false; //idk player can click a button and give up
        while (!giveUp && Board.endGame() == 0){ 
            Object[] move = selectPiece(); 
            Piece moving = (Piece)move[0]; // player select which piece they want to move
            int row = (int)move[1]; //player choose where to move piece
            int col = (int)move[2]; //player choose where to move piece
            moving.move(row, col, false); 
            b.setWhitesTurn(!b.getWhitesTurn()); 
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
        Piece chosen = Board.getBoard()[0][0]; //player click on the piece
         do{
             
             ArrayList<int[]> legalMoves = chosen.generateLegalMoves(); 
             for(int[] item : legalMoves){
                 //insert fancy code to color the square on the board a different color
            }
            
            int[] playerSelection = new int[] {}; // player select a square on the board
            while(true){
                if(legalMoves.contains(playerSelection)){// if player select one of the legal moves
                    ans[0] = new Object(); //set ans[0] to the piece chosen
                    ans[1] = legalMoves.get(0)[0]; // set ans[1] to the row
                    ans[2] = legalMoves.get(0)[1]; //set ans[2] to the col
                    break; 
                } else if (Board.getBoard()[playerSelection[0]][playerSelection[1]].pieceColor){ // else if player selected another piece they own on the board
                    chosen = Board.getBoard()[playerSelection[0]][playerSelection[1]]; // set chosen to the piece they selected and loop again 
                    break; 
                } else{ //player select a square that is not legal move or opponent piece 
                    playerSelection = new int[] {}; // player select a square on the board
                }
            }
                
        } while (ans[0] == null) ; 
        return ans;          
    }   
}
