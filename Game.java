import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener {
    // singleton setup
    public static Game g = null;

    public static Game get() {
        return g;
    }

    public static void create() {
        g = new Game();
    }

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;
    private Image world;
    // keep a reference to the timer object that triggers actionPerformed() in case
    // we need access to it in another method
    private Timer timer;
    @SuppressWarnings("unused")
    private Shop shop;
    private Point camera;
    private Tree tree;
    private NPC susan;
    //private TextAnimator text;

    @SuppressWarnings("unused")
    private int scene; // 0 is default (open world), 1 is garden/farm, 2 is

    // key press tracking
    // for moving player in directionss
    private int directionKey; // key code
    

    public int getDirectionKey() {
        return directionKey;
    }

    // checking e for special interactions
    private boolean e; // keep track of e pressed or not
    private boolean fireE; // fire for one frame
    
    //checking for options;;;;;; painnnn
    private int optionKey; // key code
    private boolean o1; //keep track of 1 pressed or not
    private boolean o2; //im gonna kms
    
    public int getOptionKey() {
        return optionKey;
    }
    
    public boolean getE() {
        return fireE;
    }
    public void setE() {
        fireE=false;
    }
    
    private Game() {
        // jswing stuff
        // set the game board size
        setPreferredSize(new Dimension(800, 600));
        loadImage();

        // initialize the game state
        directionKey = -1;
        e=false;
        fireE=false;
        optionKey=0;
        o1=false;
        o2=false;
        Player.create();
        camera = new Point();

        // testing dummies
        //tree = new Tree(new Point(200, 200),0,0,0,0);
        ArrayList<Option> opt=new ArrayList<Option>();
        opt.add(new Option("option 1",new Dialogue("i hope i get a 5!!!",null)));
        opt.add(new Option("option 2",new Dialogue("i hope i get an A",null)));

        susan = new NPC("Susan", new Point(-200, -200), new Dialogue("i love csa",opt));
       // text = new TextAnimator("i love ap csa :)");
        // farm=new Farm(1,1);
        // jswing stuff again
        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadImage() {
        try {
            world = ImageIO.read(new File("images/world.png")).getScaledInstance(2400, 1800, 1);
            while (world == null)
                ; // will change istg on god
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.

        // camera stuff, maybe move to a different method because i actually believe in
        // function oriented programming
        camera.x += (Player.get().getPos().x - camera.x) / 10;
        camera.y += (Player.get().getPos().y - camera.y) / 10;
        // prevent cam from going out of bounds
        camera.x = Math.max(-Math.abs(getWidth() - world.getWidth(this)) / 2,
                Math.min(camera.x, Math.abs(getWidth() - world.getWidth(this)) / 2));
        camera.y = Math.max(-Math.abs(getHeight() - world.getHeight(this)) / 2,
                Math.min(camera.y, Math.abs(getHeight() - world.getHeight(this)) / 2));

        // tick tok
        Player.get().tick();
        //tree.tick();
        susan.tick();
        NPC.dialogueHandler();

        // prevent player from moving out of bounds
        Player.get().getPos().x = Math.max(-(world.getWidth(this) / 2 - 25),
                Math.min(world.getWidth(this) / 2 - 25, Player.get().getPos().x));
        Player.get().getPos().y = Math.max(-(world.getHeight(this) / 2 - 25),
                Math.min(world.getHeight(this) / 2 - 25, Player.get().getPos().y));
        //System.out.println(text.getFrame());
        //make sure e stuff only happens once        
        fireE = false;
        optionKey=0;

        // calling repaint() will trigger paintComponent() to run again, which will refresh/redraw the graphics.

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
        drawBackground(g);
        Displayable.drawAll(g);
        NPC.displayDialogue(g);
        // jswing stuff
        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    // key press hell
    @Override
    public void keyTyped(KeyEvent e) {
        /* this is not used but must be defined as part of the KeyListener interface */}

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        int key = e.getKeyCode();
        // would i still like java if it was a worm
        if (key != directionKey) {
            // change player animation based on key
            switch (key) {
                case KeyEvent.VK_UP:
                    Player.get().walkU();
                    directionKey = key;
                    break;
                case KeyEvent.VK_DOWN:
                    Player.get().walkD();
                    directionKey = key;
                    break;
                case KeyEvent.VK_RIGHT:
                    Player.get().walkR();
                    directionKey = key;
                    break;
                case KeyEvent.VK_LEFT:
                    Player.get().walkL();
                    directionKey = key;
                    break;
            }
        }
        if (key == KeyEvent.VK_E) {
            if (!this.e)
                fireE = true;
            this.e = true;
        }
        if(key == KeyEvent.VK_1) {
            if(!o1) optionKey=1;
            o1=true;
        }
        if(key == KeyEvent.VK_2) {
            if(!o2) optionKey=2;
            o2=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == directionKey) {
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
                //Player.get().idle();
            directionKey = -1;
        }
        if (key == KeyEvent.VK_E)
            this.e = false;
        if (key == KeyEvent.VK_1)
            o1=false;
        if (key==KeyEvent.VK_2)
            o2=false;
    }

    // drawing
    private void drawBackground(Graphics g) {
        g.drawImage(world,
                0 - world.getWidth(this) / 2 - camera.x + getWidth() / 2,
                0 - world.getHeight(this) / 2 - camera.y + getHeight() / 2,
                this);
    }

    // random camera getter, probably useless and should be deleted soon!
    public Point camera() {
        return camera;
    }
    // public Farm getFarm() {return null;}
}
