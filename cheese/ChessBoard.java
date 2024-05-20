import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessBoard extends JPanel implements ActionListener{
    public static ChessBoard g=null;
    
    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;
    private Image world;
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    @SuppressWarnings("unused")
    private int scene; //0 is default (open world), 1 is garden/farm, 2 is 
    public int getLastKeyPressed() {
        return lastKeyPressed;
    }
    public static ChessBoard get() {
        return g;
    }
    public static void create() {
        g=new ChessBoard();
    }
    private int lastKeyPressed; //key code
    public void setLastKeyPressed(int lastKeyPressed) {
        this.lastKeyPressed = lastKeyPressed;
    }
    private ChessBoard() {
        lastKeyPressed=-1;
        // set the game board size
        setPreferredSize(new Dimension(1200, 900));
        loadImage();
        
        
        // initialize the game state
        //farm=new Farm(1,1);
        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }
    private void loadImage() {
        try {
            world=ImageIO.read(new File("images/Game_Board.png")).getScaledInstance(1200,900,1);
            while(world==null); //will change istg on god
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.

        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // when calling g.drawImage() we can use "this" for the ImageObserver 
        // because Component implements the ImageObserver interface, and JPanel 
        // extends from Component. So "this" Board instance, as a Component, can 
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.

        //camera stuff, maybe move to a different method because i actually believe in function oriented programming
         drawBackground(g);
        Piece[][] pain=Board.getBoard();
        if(pain == null){
            //System.out.println("ho"); 
        } else{
            //System.out.println("eee"); 
            for(Piece[] row: pain){
                for(Piece p : row){
                    if( p!= null){
                        p.draw(g); 
                        
                    }
                }
            }
            
        }

        
        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }



    private void drawBackground(Graphics g) {
        //System.out.println(world.getWidth(this));
        g.drawImage(world,
        0-world.getWidth(this)/2-0+getWidth()/2,
        0-world.getHeight(this)/2-0+getHeight()/2,
        this);
    }
    //public Farm getFarm() {return null;}
}
