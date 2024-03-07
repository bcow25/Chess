import java.util.ArrayList;
// hi
public class Board {
    public abstract class Piece {
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
         //test if a move will put your king in check,out of bounds, or collides with your own piece
        //true means is a valid move
        protected boolean testMove(int r,int c) {
            if(r<0||r>7||c<0||c>7) return false;
            if(pieces[r][c]!= null && pieces[r][c].pieceColor==this.pieceColor) return false;
            Piece t=pieces[r][c];
            int tr=row;
            int tc=col;
            move(r,c);
            boolean a=inCheck();
            move(tr,tc);
            pieces[r][c]=t;
            return !a;
        }
        public abstract ArrayList<int[]> generateLegalMoves();
        public abstract String getName(); 

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
        public String getName(){return "pawn:" + pieceColor;}
        
        public void setFirstMove(boolean bool){ firstMove = bool; }
        public void setCanGetFrenched(boolean bool){ canGetFrenched = bool; }
        public boolean getFirstMove(){ return firstMove;  }
        public boolean getCanGetFrenched(){ return canGetFrenched; }
        
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> moves = new ArrayList<int[]>(); 
            if(firstMove){//if is Pawn's first move then can move two blocks 
                if(pieceColor){ // if is white 
                    if(pieces[row-2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        if(testMove(row,col))
                        {   moves.add( new int[] {row - 2, col} );      
                        System.out.print("hi"); 
                        
                        }               
                } else { //color is black 
                    if(pieces[row+2][col] == null) // if 2 space ahead is unoccupied, add to moves
                        if(testMove(row,col)){
                            moves.add( new int[] {row + 2, col} );
                            System.out.print("hi"); 
                        } 
                }                                   
            }
             //check if pawn can move forward
             if(pieceColor){// if is white
                 if(row != 0 && pieces[row-1][col] == null) // if not on top row and space ahead is unoccupied, add to moves
                    if(testMove(row,col)){
                        moves.add( new int[] {row - 1, col} );
                        System.out.print("hi"); 
                    } 
             } else { //color is black 
                 if(row != 7 && pieces[row+1][col] == null) // if not on botton row and space ahead is unoccupied, add to moves
                    if(testMove(row,col)){
                        System.out.print("hi"); 
                        moves.add( new int[] {row + 1, col} );
                    } 
             }
             
             // check if can capture 
             if (pieceColor && row != 0){ // if is white
                 if(col != 7 &&  pieces[row-1][col+1] != null && pieces[row-1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     if(testMove(row,col)) moves.add(new int[] {row -1, col +1}); 
                 if (col != 0 && pieces[row-1][col-1] != null && pieces[row-1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     if(testMove(row,col)) moves.add(new int[] {row -1, col - 1}); 
             } else if (row != 7){ //piece is black
                 if(col != 7 && pieces[row+1][col+1] != null && pieces[row+1][col+1].pieceColor != pieceColor) // if there's a piece diagonally left to be captured, add to legal moves  
                     if(testMove(row,col)) moves.add(new int[] {row +1, col +1}); 
                 if (col != 0 && pieces[row+1][col-1] != null && pieces[row+1][col-1].pieceColor != pieceColor) // if there's a piece diagonally right to be captured, add to legal moves  
                     if(testMove(row,col)) moves.add(new int[] {row +1, col - 1}); 
             }
             
             //en Passent
             if ((pieceColor && row == 3) || (!pieceColor && row == 4)){ //if is on row that can en passant for corresponding color 
                 if(col != 7 && pieces[row][col + 1] instanceof Pawn ){ // check right
                     Pawn pwn = (Pawn)  pieces[row][col + 1]; 
                     if(pwn.getCanGetFrenched()) 
                         if(testMove(row,col)) moves.add(new int[] {row, col + 1});                      
                 } 
                 if (col != 0 && pieces[row][col - 1] instanceof Pawn){ // check left
                     Pawn pwn = (Pawn)  pieces[row][col - 1]; 
                     if(pwn.getCanGetFrenched())
                        if(testMove(row,col)) moves.add(new int[] {row, col - 1}); 
                 }
                 
             }             
             
             
            return moves;
        }
    }
    /**
     * ROOK:
     * can move vertically or horizontally as long as it's not blocked by other pieces
     */
    private class Rook extends Piece {
        //constructor
        public Rook(int r, int c, boolean color){
            super(r, c, color); 
        }
        
        //moves to pieces[r][c]
        public void move(int r, int c) {
            super.move(r, c);
            if (pieceColor)
                castleWhite = false;
            else 
                castleBlack = false;
        }
        public String getName (){ return "rook: " + pieceColor;}
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row;
            while (r >= 0 && (pieces[r][col] == null || (pieces[r][col] != null && pieces[r][col].pieceColor != pieceColor ))) { //checking squares left of current position
                if (testMove(r, col))
                    ans.add(new int[] {r, col} );
                r--;
            }
            r = row;
            while (r <= 7 && (pieces[r][col] == null || (pieces[r][col] != null && pieces[r][col].pieceColor != pieceColor ) )) { //checking squares right of current position
                if (testMove(r,col))
                    ans.add(new int[] {r, col} );
                r++;
            }
            int c = col;
            while (c >= 0 && (pieces[row][c] == null || (pieces[row][c] != null && pieces[row][c].pieceColor != pieceColor ))) { //checking squares above current position
                if (testMove(row,col)) 
                    ans.add(new int[] {row, c} );
                c--;
            }
            c = col;
            while (c <= 7 && (pieces[row][c] == null || (pieces[row][c] != null && pieces[row][c].pieceColor != pieceColor ))) { //checking squares below current position
                if (testMove(row,col)) 
                    ans.add(new int[] {row, c} );
                c++;
            }
            return ans;
        }
    }
    
    /**
     * KNIGHT:
     * moves in an L-shape
     * can jump over other pieces (can only capture what it lands on, not what it jumps over)
     */
    private class Knight extends Piece {
        //constructor
        public Knight(int r, int c,boolean color){
            super(r,c,color); 
        }
        
        public String getName () {return "knight: " + pieceColor;} 
        
         public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            if (row - 2 >= 0 && col - 1 >= 0)
                if (pieces[row - 2][col - 1] == null || (pieces[row - 2][col - 1] != null && pieces[row - 2][col - 1].pieceColor != pieces[row][col].pieceColor)) //2 up, 1 left
                    if(testMove(row,col))
                        ans.add(new int[]{row - 2, col - 1});
            if (row - 1 >= 0 && col - 2 >= 0)
                if (pieces[row - 1][col - 2] == null || (pieces[row - 1][col - 2] == null && pieces[row - 1][col - 2].pieceColor != pieces[row][col].pieceColor)) // 1 up, 2 left
                    if(testMove(row,col))
                        ans.add(new int[]{row - 1, col - 2});
            if (row + 1 <= 7 && col - 2 >= 0)
                if (pieces[row + 1][col - 2] == null || (pieces[row + 1][col - 2] == null && pieces[row + 1][col - 2].pieceColor != pieces[row][col].pieceColor)) // 1 down, 2 left
                    if(testMove(row,col))
                        ans.add(new int[]{row + 1, col - 2});
            if (row + 2 <= 7 && col - 1 >= 0)
                if (pieces[row + 2][col - 1] == null || (pieces[row + 2][col - 1] == null && pieces[row + 2][col - 1].pieceColor != pieces[row][col].pieceColor)) // 2 down, 1 left
                    if(testMove(row,col))
                        ans.add(new int[]{row + 2, col - 1});
            if (row - 2 >= 0 && col + 1 <= 7)
                if (pieces[row - 2][col + 1] == null || (pieces[row - 2][col + 1] == null && pieces[row - 2][col + 1].pieceColor != pieces[row][col].pieceColor)) // 2 up, 1 right
                    if(testMove(row,col))
                        ans.add(new int[]{row - 2, col + 1});
            if (row - 1 >= 0 && col + 2 <= 7)
                if (pieces[row - 1][col + 2] == null || (pieces[row - 1][col + 2] == null && pieces[row - 1][col + 2].pieceColor != pieces[row][col].pieceColor)) // 1 up, 2 right
                    if(testMove(row,col))
                        ans.add(new int[]{row - 1, col + 2});
            if (row + 1 <= 7 && col + 2 <= 7)
                if (pieces[row + 1][col + 2] == null || (pieces[row + 1][col + 2] == null && pieces[row + 1][col + 2].pieceColor != pieces[row][col].pieceColor)) // 1 down, 2 right
                    if(testMove(row,col))
                        ans.add(new int[]{row + 1, col + 2});
            if (row + 2 <= 7 && col + 1 <= 7)
                if (pieces[row + 2][col + 1] == null || (pieces[row + 2][col + 1] == null && pieces[row + 2][col + 1].pieceColor != pieces[row][col].pieceColor)) // 2 down, 1 right
                    if(testMove(row,col)) 
                        ans.add(new int[]{row + 2, col + 1});
            return ans;
        }
    }
    
    /**
     * BISHOP:
     * moves diagonally as long as it's not blocked by its own pieces
     */
   
    private class Bishop extends Piece {
        public Bishop(int r, int c,boolean color){
            super(r,c,color); 
        }
        public String getName (){return "bishop:" + pieceColor;}
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> ans = new ArrayList<int[]>();
            int r = row; 
            int c = col; 
            
            while(r <= 6 && c <= 6){
                r++; 
                c++; 
                if((pieces[r][c] == null || pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
                
            }
            r = row; 
            c = col; 
            while(r >= 1 && c <= 6 ){
                r--; 
                c++; 
                if((pieces[r][c] == null || pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
            }
            r = row; 
            c = col; 
            while(r >= 1 && c >= 1){
                r--; 
                c--; 
                if((pieces[r][c] == null || pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c}); 
                }
                
            }
            r = row; 
            c = col; 
            while(r <= 6 && c >= 1){
                r++; 
                c--; 
                if((pieces[r][c] == null || pieces[r][c].pieceColor != pieceColor) && testMove(r,c)){
                    ans.add(new int[]{r,c});
                } 
            }
            return ans;
        }
    }
       private class Queen extends Piece {
        public Queen(int r, int c,boolean color){
            super(r,c,color); 
        }
        
        public String getName(){ return "queen:" + pieceColor; }
        /*
        public void move(int r,int c) {
            if((r + 2 == row || r - 2 == row || r == row) && (c + 2 == col || c - 2 == col || c == col)){
                if(r == row){
                    if (c + 2== col){
                        pieces[r][c-1] = null; 
                    } else {
                        pieces[r][c+1] = null; 
                    }
                } else if (c == col){
                    if( r + 2 == row){
                        pieces[r-1][c] = null; 
                    } else {
                        pieces[r+1][c] = null; 
                    }
                }
            }
            pieces[r][c]=this;
            pieces[row][col]=null;
            row=r;
            col=c;
        }
        */
        // returns an arrayList of possible capture moves to male generate legal move more readable 
        // see how queen capture in README
         public ArrayList<int[]> generateCaptureMoves() {
            ArrayList <int[]> ans = new ArrayList<>(); 
            if(row > 1){
                if (pieces[row-1][col] != null && pieces[row-1][col].pieceColor != pieceColor && pieces[row-2][col] == null && testMove(row-2, col)) ans.add(new int[]{row-2, col}); 
                if(col > 1 && pieces[row-1][col-1] != null && pieces[row-1][col-1].pieceColor != pieceColor && pieces[row-2][col-2] == null && testMove(row-2, col-2)) ans.add(new int[]{row-2, col-2}); 
                if(col < 6 && pieces[row-1][col+1] != null && pieces[row-1][col+1].pieceColor != pieceColor && pieces[row-2][col+2] == null && testMove(row-2, col+2)) ans.add(new int[]{row-2, col+2}); 
            }
            
            if(row < 6){
                if (pieces[row+1][col] != null && pieces[row+1][col].pieceColor != pieceColor && pieces[row+2][col] == null && testMove(row+2, col)) ans.add(new int[]{row+2, col}); 
                if(col > 1 && pieces[row+1][col-1] != null && pieces[row+1][col-1].pieceColor != pieceColor && pieces[row+2][col-2] == null && testMove(row+2, col-2)) ans.add(new int[]{row+2, col-2}); 
                if(col < 6 && pieces[row+1 ][col+1] != null && pieces[row+1 ][col+1].pieceColor != pieceColor && pieces[row+2][col+2] == null && testMove(row+1, col+1)) ans.add(new int[]{row+2, col+2}); 
            }
            
            if (col != 0 && pieces[row][col-1] != null && pieces[row][col-1].pieceColor != pieceColor && pieces[row][col-2] == null && testMove(row, col-2)) ans.add(new int[]{row, col-2}); 
            if (col != 7 && pieces[row][col+1] != null && pieces[row][col+1].pieceColor != pieceColor && pieces[row][col + 2] == null && testMove(row, col+1)) ans.add(new int[]{row, col+2}); 
            
            return ans;
        }
        
        public ArrayList<int[]> generateLegalMoves() {
            boolean canMove = false; 
            ArrayList <int[]> ans = generateCaptureMoves(); //add list of capture moves to ans 
            for(int r = row; r <= 7; r ++){
                if(pieces[r][col] != null && pieces[r][col].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && pieces[r][col] == null && testMove(r, col)) ans.add(new int[]{r, col});                     
            }
            canMove = false; 
            for(int r = row; r >= 0; r --){
                if(pieces[r][col] != null && pieces[r][col].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && pieces[r][col] == null && testMove(r, col)) ans.add(new int[]{r, col});                     
            }
            
             canMove = false; 
            for(int c = col; c >= 0; c --){
                if(pieces[row][c] != null && pieces[row][c].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && pieces[row][c] == null && testMove(row, c)) ans.add(new int[]{row, c});                     
            }
            canMove = false; 
            for(int c = row; c <= 7; c ++){
                if(pieces[row][c] != null && pieces[row][c].pieceColor == pieceColor) canMove = true; // if has piece of same color on row. then can move
                if(canMove && pieces[row][c] == null && testMove(row, c)) ans.add(new int[]{row, c});                     
            }
            canMove = false; 
            int r = row; 
            int c = col; 
           while( r >= 1 && c >= 1){
               r--; 
               c--; 
               if(pieces[r][c] != null && pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           r = row; 
           c = col; 
           canMove = false; 
           while( r <= 6 && c >= 1){
               r++; 
               c--; 
               if(pieces[r][c] != null && pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           
           canMove = false; 
           r = row; 
           c = col; 
           while( r <= 6 && c <= 6){
               r++; 
               c++; 
               if(pieces[r][c] != null && pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           canMove = false; 
           r = row; 
           c = col; 
           while( r >= 1 && c <= 6){
               r--; 
               c++; 
               if(pieces[r][c] != null && pieces[r][c].pieceColor == pieceColor) canMove = true; 
               if(canMove && pieces[r][c] == null && testMove(r,c)) ans.add(new int[] {r,c});

           }
           return ans; 
        }
    }
    private class King extends Piece {
       public King(int r, int c,boolean color){
            super(r,c,color); 
        }
       
       public String getName (){ return "king:" + pieceColor; }
        public void move(int r,int c) {
            if(c-col==2) pieces[r][7].move(r,5);
            if(c-col==-2) pieces[r][0].move(r, 3);
            super.move(r, c);
            if(pieceColor) castleWhite=false;
            else castleBlack=false;
        }
        public ArrayList<int[]> generateLegalMoves() {
            ArrayList<int[]> a=new ArrayList<>();
            for(int r=row-1;r<=row+1;r++) 
                for(int c=col-1;c<=col+1;c++)
                if(testMove(r,c)) a.add(new int[]{r,c});
            boolean c=pieceColor?castleWhite:castleBlack;
            if(c&&pieces[row][col+1]==null&&pieces[row][col+2]==null&&testMove(row,col+1)&&testMove(row,col+2)&&!inCheck()) a.add(new int[]{row,col+2});
            if(c&&pieces[row][col-1]==null&&pieces[row][col-2]==null&&pieces[row][col-3]==null&&testMove(row,col-1)&&testMove(row,col-2)&&!inCheck()) a.add(new int[]{row,col-2});
            return a;
        }
    }
    private Piece[][] pieces;
    private boolean inCheck() {
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
    private boolean whitesTurn;
    private boolean castleWhite;
    private boolean castleBlack;
    private King bKing;
    private King wKing;
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
            //pieces[1][i]=new Pawn(1,i,false);
            //pieces[6][i]=new Pawn(6,i,true);
        }
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
