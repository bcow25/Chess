import java.util.ArrayList;
public class Board {
    private abstract class Piece {
        protected boolean pieceColor; //true means white
        protected int row;
        protected int column;
        public void move(int r,int c) {
            pieces[r][c]=this;
            pieces[row][column]=null;
            row=r;
            column=c;
        }
        public abstract ArrayList<int[]> generateLegalMoves();
    }
    private class Pawn extends Piece {
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Knight extends Piece {
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Bishop extends Piece {
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class Queen extends Piece {
        public ArrayList<int[]> generateLegalMoves() {
            return null;
        }
    }
    private class King extends Piece {
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
