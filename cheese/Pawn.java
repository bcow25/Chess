import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Point;

public class Pawn extends Piece {
    private Image killme;
        protected Image getImage(){
            return killme; 
        }
        protected void loadImage() {
            try {
    
                killme = ImageIO.read(new File("images/player.jpg")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
                while (killme == null); //most sane code written by tracy
                } catch (IOException exc) {
                System.out.println("Error opening image file: " + exc.getMessage());
            }
        }
        private boolean firstMove;
        private boolean canGetFrenched; //en passant 
        //constructor 
        public Pawn(int r, int c, boolean color){
            super(r,c, color); 
            firstMove = true; 
            canGetFrenched = false; 
            pos=new Point(Board.squareSize*r + Board.borderSizeH,Board.squareSize*c + Board.borderSizeW); 
        }
        @Override
        public String toString (){  
           if(pieceColor){
                return("White Pawn at " + row + ", " + col); 
            } else {return("Black Pawn at " + row + ", " + col); }
        }
        
        public void setFirstMove(boolean bool){ firstMove = bool; }
        public void setCanGetFrenched(boolean bool){ canGetFrenched = bool; }
        public boolean getFirstMove(){ return firstMove;  }
        public boolean getCanGetFrenched(){ return canGetFrenched; }
        
        @Override
        public void move(int r, int c, boolean test){
            if(test){
                super.move(r, c, false); 
            } else{ 
                canGetFrenched = r == row + 2 || r == row - 2;
                Board.getBoard()[r][c]=this;
                Board.getBoard()[row][col]=null;
                firstMove = false; 
                if(row < r){
                    for(int i = row; i < r; i++){
                        pos.y += Board.squareSize; 
                    }
                } else{
                    for(int i = r; i < row; i++){
                        pos.y -= Board.squareSize; 
                    }
                }
                if(col < c){
                    for(int i = col; i < c; i++){
                        pos.x += Board.squareSize; 
                    }
                } else{
                    for(int i = c; i < col; i++){
                        pos.x -= Board.squareSize; 
                    }
                }
                row=r;
                col=c;
                
                if(pieceColor && row == 0){ // if is white and reach end 
                    Board.getBoard()[row][col]= new Queen(row, col, pieceColor);                     
                } else if(!pieceColor && row == 7){ // if is white and reach end 
                    Board.getBoard()[row][col]= new Queen(row, col, pieceColor);                     
                }
            }
        }
        
        @Override
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> moves = new ArrayList<int[]>(); 
            if(firstMove){//if is Pawn's first move then can move two blocks 
                if(pieceColor){ // if is white 
                    if(Board.getBoard()[row-2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        if(testMove(row-2,col))
                        {   moves.add( new int[] {row - 2, col} );      
                        
                        }               
                } else { //color is black 
                    if(Board.getBoard()[row+2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        if(testMove(row+2,col)){
                            moves.add( new int[] {row + 2, col} );
                        } 
                }                                   
            }
             //check if pawn can move forward
             if(pieceColor){// if is white
                 if(row != 0 && Board.getBoard()[row-1][col] == null) // if not on top row and space ahead is unoccupied, add to moves
                    if(testMove(row-1,col)){
                        moves.add( new int[] {row - 1, col} );
                    } 
             } else { //color is black 
                 if(row != 7 && Board.getBoard()[row+1][col] == null) // if not on botton row and space ahead is unoccupied, add to moves
                    if(testMove(row+1,col)){
                        moves.add( new int[] {row + 1, col} );
                    } 
             }
             
             // check if can capture 
             if (pieceColor && row != 0){ // if is white
                 if(col != 7 &&  Board.getBoard()[row-1][col+1] != null && Board.getBoard()[row-1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     if(testMove(row-1,col+1)) moves.add(new int[] {row -1, col +1}); 
                 if (col != 0 && Board.getBoard()[row-1][col-1] != null && Board.getBoard()[row-1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     if(testMove(row-1,col-1)) moves.add(new int[] {row -1, col - 1}); 
             } else if (row != 7){ //piece is black
                 if(col != 7 && Board.getBoard()[row+1][col+1] != null && Board.getBoard()[row+1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     if(testMove(row+1,col+1)) moves.add(new int[] {row +1, col +1}); 
                 if (col != 0 && Board.getBoard()[row+1][col-1] != null && Board.getBoard()[row+1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     if(testMove(row+1,col-1)) moves.add(new int[] {row +1, col - 1}); 
             }
             
             //en Passent
             if ((pieceColor && row == 3) || (!pieceColor && row == 4)){ //if is on row that can en passant for corresponding color 
                 if(col != 7 && Board.getBoard()[row][col + 1] instanceof Pawn ){ // check right
                     Pawn pwn = (Pawn)  Board.getBoard()[row][col + 1]; 
                     if(pwn.getCanGetFrenched()) 
                         if(testMove(row,col+1)) moves.add(new int[] {row, col + 1});                      
                 } 
                 if (col != 0 && Board.getBoard()[row][col - 1] instanceof Pawn){ // check left
                     Pawn pwn = (Pawn)  Board.getBoard()[row][col - 1]; 
                     if(pwn.getCanGetFrenched())
                        if(testMove(row,col-1)) {
                            moves.add(new int[] {row, col - 1});
                        } 
                 }
                 
             }             
             
             
            return moves;
        }
    }
