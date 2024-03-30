import java.util.ArrayList;
// hi
public class Board {
    public static Piece[][] pieces;
    public static boolean inCheck() {
        King c=whitesTurn?wKing:bKing;
        for(int i=c.row;i>=0;i--) {
            if(pieces[i][c.col]==null) continue;
            if(pieces[i][c.col].pieceColor!=whitesTurn&&(pieces[i][c.col] instanceof Queen || pieces[i][c.col] instanceof Rook))
               return true;
            if(pieces[i][c.col]!=null) break;
            
        }
        for(int i=c.row;i<8;i++) {
            if(pieces[i][c.col]==null) continue;
            if(pieces[i][c.col].pieceColor!=whitesTurn&&(pieces[i][c.col] instanceof Queen || pieces[i][c.col] instanceof Rook))
               return true;
            if(pieces[i][c.col]!=null) break;
            
        }
        for(int i=c.col;i>=0;i--) {
            if(pieces[c.row][i]==null) continue;
            if(pieces[c.row][i].pieceColor!=whitesTurn&&(pieces[c.row][i] instanceof Queen || pieces[c.row][i] instanceof Rook))
               return true;
            if(pieces[c.row][i]!=null) break;
            
        }
        for(int i=c.col;i<8;i++) {
            if(pieces[c.row][i]==null) continue;
            if(pieces[c.row][i].pieceColor!=whitesTurn&&(pieces[c.row][i] instanceof Queen || pieces[c.row][i] instanceof Rook))
               return true;
            if(pieces[c.row][i]!=null) break;
            
        }
        int j=c.col;
        for(int i=c.row;i>=0&&j>=0;i--) {
            if(pieces[i][j]==null) continue;
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[i][j]!=null) break;
            j--;
        }
        
        j=c.col;
        for(int i=c.row;i<8&&j>=0;i++) {
            if(pieces[i][j]==null) continue;
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[i][j]!=null) break;
            j--;
        }
        j=c.col;
        for(int i=c.row;i>=0&&j<8;i--) {
            if(pieces[i][j]==null) continue;
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[i][j]!=null) break;
            j++;
        }
        j=c.col;
        for(int i=c.row;i<8&&j<0;i++) {
            if(pieces[i][j]==null) continue;
            if(pieces[i][j].pieceColor!=whitesTurn&&(pieces[i][j] instanceof Queen || pieces[i][j] instanceof Bishop))
               return true;
            if(pieces[i][j]!=null) break;
            j++;
        }
        int[][] help={{c.row-2,c.col+1},{c.row-1,c.col+2},{c.row+1,c.col+2},{c.row+2,c.col+1},{c.row-2,c.col-1},{c.row-1,c.col-2},{c.row+1,c.col-2},{c.row+2,c.col-1}};
        for(int[] i:help)
            if(i[0]<8&&i[0]>=0&&i[1]<8&&i[1]>=0) if(pieces[i[0]][i[1]] instanceof Knight && pieces[i[0]][i[1]].pieceColor!=whitesTurn) return true;
        for(int i=c.row-1;i<=c.row+1;i++)
            for(int k=c.col-1;k<=c.col+1;k++) 
                if(i>=0&&i<8&&k>=0&&j<8&&pieces[i][k] instanceof King&&pieces[i][k].pieceColor!=c.pieceColor) 
                    return true;
        int r=c.row+(c.pieceColor?1:-1);
        if(r>=0&&r<8&&c.col-1>=0&&c.col-1<8&&pieces[r][c.col-1] instanceof Pawn&&pieces[c.row+(c.pieceColor?1:-1)][c.col-1].pieceColor!=c.pieceColor) 
            return true;
        if(r>=0&&r<8&&c.col+1>=0&&c.col+1<8&&pieces[r][c.col+1] instanceof Pawn&&pieces[c.row+(c.pieceColor?1:-1)][c.col+1].pieceColor!=c.pieceColor) 
            return true;
        return false;
    }
    private static boolean whitesTurn;
    public static boolean castleWhite;
    public static boolean castleBlack;
    private static King bKing;
    private static King wKing;
    private int moves; //for tracking draws
    private ArrayList<Integer> repeatedPositions;
    /**Arrange pieces into the default starting position.**/
    public Board() {
        whitesTurn=true;
        castleWhite=true;
        castleBlack=true;
        moves=0;
        repeatedPositions=new ArrayList<Integer>();
        bKing=new King(0,4,false);
        wKing=new King(7,4,true);
        pieces=new Piece[8][8];
        Piece[] t={new Rook(0,0,false),new Knight(0,1,false),new Bishop(0,2,false),new Queen(0,3,false),bKing,new Bishop(0,5,false),new Knight(0,6,false),new Rook(0,7,false)};
        pieces[0]=t;
        t=new Piece[]{new Rook(7,0,true),new Knight(7,1,true),new Bishop(7,2,true),new Queen(7,3,true),wKing,new Bishop(7,5,true),new Knight(7,6,true),new Rook(7,7,true)};
        pieces[7]=t;
        for(int i=0;i<8;i++) {
            pieces[1][i]=new Pawn(1,i,false);
            pieces[6][i]=new Pawn(6,i,true);
        }
    }
    public static Piece[][] getBoard() {return pieces;}
    public static boolean contains(Piece p){
        for(Piece[] row : pieces)
            for(Piece piece : row)
                if (piece == p) return true; 
        return false; 
    }
    
    // 0: game continue
    // 1: white wins (black king captured)
    // 2: black wins (white king captured) 
    public static int endGame() {
        if(!contains(bKing)) return 1; 
        if (!contains(wKing)) return 2; 
        return 0; 
    }
    
}
