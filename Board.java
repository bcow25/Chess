import java.util.ArrayList;
public class Board {
    private abstract class Piece {
        public Piece (int r, int c){
            row = r; 
            col = c;
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
        public Pawn(int r, int c){
            super(r,c); 
        }
            
        
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Rook extends Piece {
        public Rook(int r, int c){
            super(r,c); 
        }
            
        
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Knight extends Piece {
        public Knight(int r, int c){
            super(r,c); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            if (row - 2 >= 0 && col - 1 >= 0)
                ans.add(new int[]{row - 2, col - 1});
            if (row - 1 >= 0 && col - 2 >= 0)
                ans.add(new int[]{row - 1, col - 2});
            if (row + 1 <= 7 && col - 2 >= 0)
                ans.add(new int[]{row + 1, col - 2});
            if (row + 2 <= 7 && col - 1 >= 0)
                ans.add(new int[]{row + 2, col - 1});
            if (row - 2 >= 0 && col + 1 <= 7)
                ans.add(new int[]{row - 2, col + 1});
            if (row - 1 >= 0 && col + 2 <= 7)
                ans.add(new int[]{row - 1, col + 2});
            if (row + 1 <= 7 && col + 2 <= 7)
                ans.add(new int[]{row + 1, col + 2});
            if (row + 2 <= 7 && col + 1 <= 7)
                ans.add(new int[]{row + 1, col + 2});
            return ans;
        }
    }
    private class Bishop extends Piece {
        public Bishop(int r, int c){
            super(r,c); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Queen extends Piece {
        public Queen(int r, int c){
            super(r,c); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class King extends Piece {
        public King(int r, int c){
            super(r,c); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private Piece[][] pieces;
    private boolean inCheck() {return false;}
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
