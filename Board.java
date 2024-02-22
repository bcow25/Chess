import java.util.ArrayList;
public class Board {
    private abstract class Piece {
        public Piece (int r, int c,boolean color){
            row = r; 
            col = c;
            pieceColor=color;
        }
        protected boolean pieceColor; //true means white
        protected int row;
        protected int col;
        public void move(int r,int c) {
            pieces[r][c]=this;
            pieces[row][col]=null;
            row=r;
            col=c;
        }
        public abstract ArrayList<int[]> generateLegalMoves();
    }
     private class Pawn extends Piece {
        private boolean firstMove;
        private boolean canGetFrenched; //en passant 
        //constructor 
        public Pawn(int r, int c, boolean color){
            super(r,c, color); 
            firstMove = true; 
            canGetFrenched = false; 
        }
        
        public void setFirstMove(boolean bool){ firstMove = bool; }
        public void setCanGetFrenched(boolean bool){ canGetFrenched = bool; }
        public boolean getFirstMove(){ return firstMove;  }
        public boolean getCanGetFrenched(){ return canGetFrenched; }
        
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> moves = new ArrayList<int[]>(); 
            if(firstMove){//if is Pawn's first move then can move two blocks 
                if(pieceColor){ // if is white 
                    if(pieces[row-2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        moves.add( new int[] {row - 2, col} );                     
                } else { //color is black 
                    if(pieces[row+2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        moves.add( new int[] {row + 2, col} ); 
                }                                   
            }
             //check if pawn can move forward
             if(pieceColor){// if is white
                 if(row != 0 && pieces[row-1][col] == null) // if not on top row and space ahead is unoccupied, add to moves
                    moves.add( new int[] {row - 1, col} ); 
             } else { //color is black 
                 if(row != 7 && pieces[row+1][col] == null) // if not on botton row and space ahead is unoccupied, add to moves
                    moves.add( new int[] {row + 1, col} ); 
             }
             
             // check if can capture 
             if (pieceColor && row != 0){ // if is white
                 if(col != 7 && pieces[row-1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     moves.add(new int[] {row -1, col +1}); 
                 if (col != 0 && pieces[row-1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     moves.add(new int[] {row -1, col - 1}); 
             } else if (row != 7){ //piece is black
                 if(col != 7 && pieces[row+1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     moves.add(new int[] {row +1, col +1}); 
                 if (col != 7 && pieces[row+1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     moves.add(new int[] {row +1, col - 1}); 
             }
             
             //en Passent
             if ((pieceColor && row == 3) || (!pieceColor && row == 4)){ //if is on row that can en passant for corresponding color 
                 if(col != 7 && pieces[row][col + 1] instanceof Pawn ){ // check right
                     Pawn pwn = (Pawn)  pieces[row][col + 1]; 
                     if(pwn.getCanGetFrenched()) 
                         moves.add(new int[] {row, col + 1});                      
                 } 
                 if (col != 0 && pieces[row][col - 1] instanceof Pawn){ // check left
                     Pawn pwn = (Pawn)  pieces[row][col - 1]; 
                     if(pwn.getCanGetFrenched())
                        moves.add(new int[] {row, col - 1}); 
                 }
                 
             }             
             
             // check if put king in check 
             for(int[] pos : moves){
                 pieces[pos[0]][pos[1]] = new Pawn(pos[0], pos[1], pieceColor); // piece move to possible position 
                 pieces[row][col] = null; // remove current piece
                 if(inCheck()){ // if bad, remove move 
                     moves.remove(pos); 
                 }                 
                 // put piece back 
                 pieces[pos[0]][pos[1]] = null; 
                 pieces[row][col] = new Pawn(row, col, pieceColor); 
             }
             
            return moves;
        }
    }
    private class Rook extends Piece {
        public Rook(int r, int c,boolean color){
            super(r,c,color); 
        }
        public void move(int r,int c) {
            super.move(r, c);
            if(pieceColor) castleWhite=false;
            else castleBlack=false;
        }
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row;
            while (r >= 0 && pieces[r][col] == null)
            {
                ans.add(new int[] {r, col});
                r--;
            }
            r = row;
            while (r <= 7 && pieces[r][col] == null)
            {
                ans.add(new int[] {r, col});
                r++;
            }
            int c = col;
            while (r >= 0 && pieces[row][c] == null)
            {
                ans.add(new int[] {row, c});
                c--;
            }
            c = col;
            while (c <= 7 && pieces[row][c] == null)
            {
                ans.add(new int[] {row, c});
                c++;
            }
            return ans;
        }
    }
    private class Knight extends Piece {
        public Knight(int r, int c,boolean color){
            super(r,c,color); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            if (row - 2 >= 0 && col - 1 >= 0 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row - 2, col - 1});
            if (row - 1 >= 0 && col - 2 >= 0 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row - 1, col - 2});
            if (row + 1 <= 7 && col - 2 >= 0 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row + 1, col - 2});
            if (row + 2 <= 7 && col - 1 >= 0 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row + 2, col - 1});
            if (row - 2 >= 0 && col + 1 <= 7 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row - 2, col + 1});
            if (row - 1 >= 0 && col + 2 <= 7 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row - 1, col + 2});
            if (row + 1 <= 7 && col + 2 <= 7 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row + 1, col + 2});
            if (row + 2 <= 7 && col + 1 <= 7 && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)
                ans.add(new int[]{row + 1, col + 2});
            return ans;
        }
    }
    private class Bishop extends Piece {
        public Bishop(int r, int c,boolean color){
            super(r,c,color); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Queen extends Piece {
        public Queen(int r, int c,boolean color){
            super(r,c,color); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class King extends Piece {
        public King(int r, int c,boolean color){
            super(r,c,color); 
        }
        public void move(int r,int c) {
            super.move(r, c);
            if(pieceColor) castleWhite=false;
            else castleBlack=false;
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private Piece[][] pieces;
    private boolean inCheck() {
        King c=whitesTurn?wKing:bKing;
        for(int i=c.row;i>=0;i--) {
            if(pieces[i][c.col].pieceColor!=whitesTurn&&(pieces[i][c.col] instanceof Queen || pieces[i][c.col] instanceof Rook))
               return true;
            if(pieces[i][c.col]!=null) break;
            
        }
        for(int i=c.row;i<8;i++) {
            
            if(pieces[i][c.col].pieceColor!=whitesTurn&&(pieces[i][c.col] instanceof Queen || pieces[i][c.col] instanceof Rook))
               return true;
            if(pieces[i][c.col]!=null) break;
            
        }
        for(int i=c.col;i>=0;i--) {
            if(pieces[c.row][i].pieceColor!=whitesTurn&&(pieces[c.row][i] instanceof Queen || pieces[c.row][i] instanceof Rook))
               return true;
            if(pieces[c.row][i]!=null) break;
            
        }
        for(int i=c.col;i<8;i++) {
            
            if(pieces[c.row][i].pieceColor!=whitesTurn&&(pieces[c.row][i] instanceof Queen || pieces[c.row][i] instanceof Rook))
               return true;
            if(pieces[c.row][i]!=null) break;
            
        }
        int j=c.col;
        for(int i=c.row;i>=0&&j>=0;i--) {
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[c.row][i]!=null) break;
            j--;
        }
        
        j=c.col;
        for(int i=c.row;i<8&&j>=0;i++) {
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[c.row][i]!=null) break;
            j--;
        }
        j=c.col;
        for(int i=c.row;i>=0&&j<8;i--) {
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[c.row][i]!=null) break;
            j++;
        }
        j=c.col;
        for(int i=c.row;i<8&&j<0;i++) {
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[c.row][i]!=null) break;
            j++;
        }
        int[][] help={{c.row-2,c.col+1},{c.row-1,c.col+2},{c.row+1,c.col+2},{c.row+2,c.col+1},{c.row-2,c.col-1},{c.row-1,c.col-2},{c.row+1,c.col-2},{c.row+2,c.col-1}};
        for(int[] i:help)
            if(i[0]<8&&i[0]>=0&&i[1]<8&&i[1]>=0) if(pieces[i[0]][i[1]] instanceof Knight && pieces[i[0]][i[1]].pieceColor!=whitesTurn) return true;
        return false;
    }
    private boolean whitesTurn;
    private boolean castleWhite;
    private boolean castleBlack;
    private King bKing;
    private King wKing;
    private int moves; //for tracking draws
    private ArrayList<Integer> repeatedPositions;
    /**Arrange pieces into the default starting position.**/
    public Board() {
        
    }
    public Piece[][] getBoard() {return pieces;}
    /* trash
    public Board(Piece[][] pieces) {
        this.pieces=pieces;
    }
    */
    //idk yet
    public void endGame() {}
    
}
