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
    private class Knight extends Piece {
        public Knight(int r, int c){
            super(r,c); 
        }
        public ArrayList<int[]> generateLegalMoves() {
            return null;
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
    private boolean inCheck;
    private boolean whitesTurn;
    private boolean castleWhiteLeft;
    private boolean castleWhiteRight;
    private boolean castleBlackLeft;
    private boolean castleBlackRight;
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
