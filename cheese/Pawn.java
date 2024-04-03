import java.util.ArrayList;

public class Pawn extends Piece {
        private boolean firstMove;
        private boolean canGetFrenched; //en passant 
        //constructor 
        public Pawn(int r, int c, boolean color){
            super(r,c, color); 
            firstMove = true; 
            canGetFrenched = false; 
        }
        public String toString(){return "pawn:" + pieceColor;}
        
        public void setFirstMove(boolean bool){ firstMove = bool; }
        public void setCanGetFrenched(boolean bool){ canGetFrenched = bool; }
        public boolean getFirstMove(){ return firstMove;  }
        public boolean getCanGetFrenched(){ return canGetFrenched; }
        
        public void move(int r, int c){
            if(r == row + 2 || r == row - 2){
                canGetFrenched = true; 
            }else{
                canGetFrenched = false; 
            }
            Board.pieces[r][c]=this;
            Board.pieces[row][col]=null;
            firstMove = false; 
            row=r;
            col=c;
        }
        
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> moves = new ArrayList<int[]>(); 
            if(firstMove){//if is Pawn's first move then can move two blocks 
                if(pieceColor){ // if is white 
                    if(Board.pieces[row-2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        if(testMove(row-2,col))
                        {   moves.add( new int[] {row - 2, col} );      
                        
                        }               
                } else { //color is black 
                    if(Board.pieces[row+2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        if(testMove(row+2,col)){
                            moves.add( new int[] {row + 2, col} );
                        } 
                }                                   
            }
             //check if pawn can move forward
             if(pieceColor){// if is white
                 if(row != 0 && Board.pieces[row-1][col] == null) // if not on top row and space ahead is unoccupied, add to moves
                    if(testMove(row-1,col)){
                        moves.add( new int[] {row - 1, col} );
                    } 
             } else { //color is black 
                 if(row != 7 && Board.pieces[row+1][col] == null) // if not on botton row and space ahead is unoccupied, add to moves
                    if(testMove(row+1,col)){
                        moves.add( new int[] {row + 1, col} );
                    } 
             }
             
             // check if can capture 
             if (pieceColor && row != 0){ // if is white
                 if(col != 7 &&  Board.pieces[row-1][col+1] != null && Board.pieces[row-1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     if(testMove(row-1,col+1)) moves.add(new int[] {row -1, col +1}); 
                 if (col != 0 && Board.pieces[row-1][col-1] != null && Board.pieces[row-1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     if(testMove(row-1,col-1)) moves.add(new int[] {row -1, col - 1}); 
             } else if (row != 7){ //piece is black
                 if(col != 7 && Board.pieces[row+1][col+1] != null && Board.pieces[row+1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     if(testMove(row+1,col+1)) moves.add(new int[] {row +1, col +1}); 
                 if (col != 0 && Board.pieces[row+1][col-1] != null && Board.pieces[row+1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     if(testMove(row+1,col-1)) moves.add(new int[] {row +1, col - 1}); 
             }
             
             //en Passent
             if ((pieceColor && row == 3) || (!pieceColor && row == 4)){ //if is on row that can en passant for corresponding color 
                 if(col != 7 && Board.pieces[row][col + 1] instanceof Pawn ){ // check right
                     Pawn pwn = (Pawn)  Board.pieces[row][col + 1]; 
                     if(pwn.getCanGetFrenched()) 
                         if(testMove(row,col+1)) moves.add(new int[] {row, col + 1});                      
                 } 
                 if (col != 0 && Board.pieces[row][col - 1] instanceof Pawn){ // check left
                     Pawn pwn = (Pawn)  Board.pieces[row][col - 1]; 
                     if(pwn.getCanGetFrenched())
                        if(testMove(row,col-1)) {
                            moves.add(new int[] {row, col - 1});
                        } 
                 }
                 
             }             
             
             
            return moves;
        }
    }
