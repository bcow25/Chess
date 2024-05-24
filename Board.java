
// hi

import java.util.ArrayList;

public class Board {
    private static Piece[][] pieces;
    protected static final int borderSizeH = -170;
    protected static final int squareSize = 49;
    protected static final int borderSizeW = -165;

    public static void setPiece(int r, int c, Piece p) {
        pieces[r][c] = p;
    }

    /*
     * trash method that we never debugged
     * public static boolean inCheck() {
     * King c=whitesTurn?wKing:bKing;
     * for(int i=c.row;i>=0;i--) {
     * if(pieces[i][c.col]==null) continue;
     * if(pieces[i][c.col].pieceColor!=whitesTurn&&pieces[i][c.col] instanceof Rook)
     * return true;
     * if(pieces[i][c.col]!=null) break;
     * 
     * }
     * for(int i=c.row;i<8;i++) {
     * if(pieces[i][c.col]==null) continue;
     * if(pieces[i][c.col].pieceColor!=whitesTurn&& pieces[i][c.col] instanceof
     * Rook)
     * return true;
     * if(pieces[i][c.col]!=null) break;
     * 
     * }
     * for(int i=c.col;i>=0;i--) {
     * if(pieces[c.row][i]==null) continue;
     * if(pieces[c.row][i].pieceColor!=whitesTurn&& pieces[c.row][i] instanceof
     * Rook)
     * return true;
     * if(pieces[c.row][i]!=null) break;
     * 
     * }
     * for(int i=c.col;i<8;i++) {
     * if(pieces[c.row][i]==null) continue;
     * if(pieces[c.row][i].pieceColor!=whitesTurn&& pieces[c.row][i] instanceof
     * Rook)
     * return true;
     * if(pieces[c.row][i]!=null) break;
     * 
     * }
     * int j=c.col;
     * for(int i=c.row;i>=0&&j>=0;i--) {
     * if(pieces[i][j]==null) continue;
     * if(pieces[i][j].pieceColor!=whitesTurn&& pieces[i][j] instanceof Bishop)
     * return true;
     * if(pieces[i][j]!=null) break;
     * j--;
     * }
     * 
     * j=c.col;
     * for(int i=c.row;i<8&&j>=0;i++) {
     * if(pieces[i][j]==null) continue;
     * if(pieces[i][j].pieceColor!=whitesTurn&&pieces[i][j] instanceof Bishop)
     * return true;
     * if(pieces[i][j]!=null) break;
     * j--;
     * }
     * j=c.col;
     * for(int i=c.row;i>=0&&j<8;i--) {
     * if(pieces[i][j]==null) continue;
     * if(pieces[i][j].pieceColor!=whitesTurn&& pieces[i][j] instanceof Bishop)
     * return true;
     * if(pieces[i][j]!=null) break;
     * j++;
     * }
     * j=c.col;
     * for(int i=c.row;i<8&&j<0;i++) {
     * if(pieces[i][j]==null) continue;
     * if(pieces[i][j].pieceColor!=whitesTurn&& pieces[i][j] instanceof Bishop)
     * return true;
     * if(pieces[i][j]!=null) break;
     * j++;
     * }
     * 
     * //Queen stuff
     * int row = c.row;
     * int col = c.col;
     * if(row < 7 && row > 0){
     * if (pieces[row + 1][col]!= null && pieces[row + 1][col] instanceof Queen &&
     * pieces[row+1][col].pieceColor != whitesTurn && pieces[row - 1][col] == null)
     * return true;
     * if (pieces[row + 1][col] != null && pieces[row - 1][col] instanceof Queen &&
     * pieces[row-1][col].pieceColor != whitesTurn && pieces[row + 1][col] == null)
     * return true;
     * }
     * if (col < 7 && col > 0){
     * if (pieces[row][col+1] != null && pieces[row][col-1] instanceof Queen &&
     * pieces[row][col-1].pieceColor !=whitesTurn && pieces[row][col+1] == null)
     * return true;
     * if (pieces[row][col+1] != null && pieces[row][col+1] instanceof Queen &&
     * pieces[row][col+1].pieceColor !=whitesTurn && pieces[row][col-1] == null)
     * return true;
     * }
     * 
     * if (col < 7 && col > 0 && row < 7 && row > 0){
     * if (pieces[row + 1][col] != null && pieces[row - 1][col-1] instanceof Queen
     * && pieces[row-1][col].pieceColor !=whitesTurn && pieces[row+1][col+1] ==
     * null) return true;
     * if (pieces[row + 1][col]!= null && pieces[row - 1][col+1] instanceof Queen &&
     * pieces[row-1][col].pieceColor !=whitesTurn && pieces[row+1][col-1] == null)
     * return true;
     * if (pieces[row + 1][col] != null && pieces[row + 1][col-1] instanceof Queen
     * && pieces[row+1][col-1].pieceColor !=whitesTurn && pieces[row -1 ][col+1] ==
     * null) return true;
     * if (pieces[row + 1][col] != null && pieces[row + 1][col+1] instanceof Queen
     * && pieces[row+1][col+1].pieceColor !=whitesTurn&& pieces[row -1][col-1] ==
     * null) return true;
     * }
     * 
     * int[][]
     * help={{c.row-2,c.col+1},{c.row-1,c.col+2},{c.row+1,c.col+2},{c.row+2,c.col+1}
     * ,{c.row-2,c.col-1},{c.row-1,c.col-2},{c.row+1,c.col-2},{c.row+2,c.col-1}};
     * for(int[] i:help)
     * if(i[0]<8&&i[0]>=0&&i[1]<8&&i[1]>=0) if(pieces[i[0]][i[1]] instanceof Knight
     * && pieces[i[0]][i[1]].pieceColor!=whitesTurn) return true;
     * for(int i=c.row-1;i<=c.row+1;i++)
     * for(int k=c.col-1;k<=c.col+1;k++)
     * if(i>=0&&i<8&&k>=0&&j<8&&pieces[i][k] instanceof
     * King&&pieces[i][k].pieceColor!=c.pieceColor)
     * return true;
     * int r=c.row+(c.pieceColor?1:-1);
     * if(r>=0&&r<8&&c.col-1>=0&&c.col-1<8&&pieces[r][c.col-1] instanceof
     * Pawn&&pieces[c.row+(c.pieceColor?1:-1)][c.col-1].pieceColor!=c.pieceColor)
     * return true;
     * return r>=0&&r<8&&c.col+1>=0&&c.col+1<8&&pieces[r][c.col+1] instanceof
     * Pawn&&pieces[c.row+(c.pieceColor?1:-1)][c.col+1].pieceColor!=c.pieceColor;
     * }
     */
    //private static boolean whitesTurn;
    private static boolean castleWhite;

    public static void setCastleWhite(boolean b) {
        castleWhite = b;
    }

    public static boolean getCastleWhite() {
        return castleWhite;
    }

    private static boolean castleBlack;

    // public static void setcastleBlack(boolean b){castleBlack = b;}
    public static void setCastleBlack(boolean b) {
        castleBlack = b;
    }

    public static boolean getCastleBlack() {
        return castleBlack;
    }

    private static King bKing;
    private static King wKing;

    // private ArrayList<Integer> repeatedPositions;
    /** Arrange pieces into the default starting position. **/
    public Board(int difficulty) {
        castleWhite = true;
        castleBlack = true;
        gameState = 0;
        // repeatedPositions=new ArrayList<Integer>();
        if (difficulty == 0) {
            boardEasy();
        } else if (difficulty == 1) {
            boardMedium();
        } else {
            boardHard();
        }

    }

    // all queens
    public void boardEasy() {

        pieces = new Piece[8][8];
        bKing = new King(0, 4, false);
        wKing = new King(7, 4, true);
        pieces[0][4] = bKing;
        pieces[7][4] = wKing;
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(1, i, false);
            pieces[6][i] = new Rook(6, i, true);
        }
        for (int i = 0; i < 8; i++) {
            if (i != 4) {
                pieces[0][i] = new Pawn(0, i, false);
                pieces[7][i] = new Rook(7, i, true);
            }
        }
    }

    // queen and horses
    public void boardMedium() {
        pieces = new Piece[8][8];
        bKing = new King(0, 4, false);
        wKing = new King(7, 4, true);
        pieces[0][4] = bKing;
        pieces[7][4] = wKing;
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Knight(1, i, false);
            pieces[6][i] = new Knight(6, i, true);
        }
        for (int i = 0; i < 8; i++) {
            if (i != 4) {
                pieces[0][i] = new Pawn(0, i, false);
                pieces[7][i] = new Rook(7, i, true);
            }
        }
    }

    // standard chess board
    public void boardHard() {
        pieces = new Piece[8][8];
        bKing = new King(0, 4, false);
        wKing = new King(7, 4, true);
        Piece[] t = { new Rook(0, 0, false), new Knight(0, 1, false), new Bishop(0, 2, false), new Queen(0, 3, false),
                bKing, new Bishop(0, 5, false), new Knight(0, 6, false), new Rook(0, 7, false) };
        pieces[0] = t;
        t = new Piece[] { new Rook(7, 0, true), new Knight(7, 1, true), new Bishop(7, 2, true), new Queen(7, 3, true),
                wKing, new Bishop(7, 5, true), new Knight(7, 6, true), new Rook(7, 7, true) };
        pieces[7] = t;
        for (int i = 2; i < 6; i++) {
            pieces[i] = new Piece[8];
        }

        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(1, i, false);
            pieces[6][i] = new Pawn(6, i, true);
        }
    }

    public static Piece[][] getBoard() {
        return pieces;
    }

    public static boolean contains(Piece p) {
        for (Piece[] row : pieces)
            for (Piece piece : row)
                if (piece == p)
                    return true;
        return false;
    }

    private static int gameState; // 0 is default, 1 is player won, 2 is player lost

    public static int getGameState() {
        return gameState;
    }

    public static void setGameState(int gameState) {
        Board.gameState = gameState;
    }

    private static Piece getRandomBlackPiece() {
        ArrayList<Piece> movables = new ArrayList<Piece>(); // all black pieces on board that can move

        // populate moveables
        for (Piece[] r : Board.getBoard()) {
            for (Piece p : r) {
                if (p != null && !p.pieceColor && !p.generateLegalMoves().isEmpty()) {
                    movables.add(p);
                }
            }
        }
        // randomly choose a piece from moveables and return
        if (movables.size() == 0) {
            return null;
        } else {
            return movables.get((int) (Math.random() * movables.size()));
        }
    }

    public static void playBlackMove() {
        if(gameState!=0) return;
        Piece moving = getRandomBlackPiece();
        if (moving == null) {
            gameState=1; //player won
            return;
        }
        int[] temp = moving.generateRandomMoves();
        moving.move(temp[0], temp[1], false);
        return;
    }
}
