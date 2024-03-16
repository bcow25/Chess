import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener {

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 12;
    public static final int COLUMNS = 18;
    // controls how many coins appear on the board
    public static final int NUM_COINS = 5;
    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;
    private Image world;
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    private Player player;
    private Point camera;
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public Game() {
        // set the game board size
        setPreferredSize(new Dimension(800, 600));
        loadImage();
        // initialize the game state
        player = new Player(this);
        camera=new Point();
        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
        up=false;
        down=false;
        left=false;
        right=false;

    }
    private void loadImage() {
        try {
            world=ImageIO.read(new File("images/world.png")).getScaledInstance(2400,1800,1);
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

        // prevent the player from disappearing off the board
        player.tick();

        // give the player points for collecting coins

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
        camera.x+=(player.getPos().x-camera.x)/10;
        camera.y+=(player.getPos().y-camera.y)/10;
        camera.x=Math.max(-Math.abs(getWidth()-world.getWidth(this))/2,Math.min(camera.x,Math.abs(getWidth()-world.getWidth(this))/2));
        camera.y=Math.max(-Math.abs(getHeight()-world.getHeight(this))/2,Math.min(camera.y,Math.abs(getHeight()-world.getHeight(this))/2));
        drawBackground(g);
        player.draw(g,this);
        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        int key = e.getKeyCode();
        
        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
            up=up||key == KeyEvent.VK_UP;
        down=down||key == KeyEvent.VK_DOWN;
        right=right||key==KeyEvent.VK_RIGHT;
        left=left||key == KeyEvent.VK_LEFT;
    }

    @Override
    public void keyReleased(KeyEvent e) {
       int key=e.getKeyCode();
       up=up&&key!=KeyEvent.VK_UP;
        down=down&&key!=KeyEvent.VK_DOWN;
        right=right&&key!=KeyEvent.VK_RIGHT;
        left=left&&key!=KeyEvent.VK_LEFT;
    }

    private void drawBackground(Graphics g) {
        //System.out.println(world.getWidth(this));
        g.drawImage(world,
        0-world.getWidth(this)/2-camera.x+getWidth()/2,
        0-world.getHeight(this)/2-camera.y+getHeight()/2,
        this);
    }
    public Point camera() {return camera;}
}
