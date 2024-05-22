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
    //protected static Plant[] items;


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
    //private Image world;
    // keep a reference to the timer object that triggers actionPerformed() in case
    // we need access to it in another method
    private Timer timer;
    @SuppressWarnings("unused")
    private Point camera;
    //private TextAnimator text;

    @SuppressWarnings("unused")
    private int scene; // 0 is default (open world), 1 is garden/farm, 2 is library, 3 is home

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

    //displayable
    private ArrayList<ArrayList<Displayable>> displays;
    private ArrayList<ArrayList<Tickable>> ticks;
    private ArrayList<Image> worlds;
    private Game() {
        // jswing stuff
        // set the game board size
        setPreferredSize(new Dimension(720, 540));

        displays=new ArrayList<ArrayList<Displayable>>();
        for(int i=0;i<5/*however many maps we have*/;i++) displays.add(null);
        ticks=new ArrayList<ArrayList<Tickable>>();
        for(int i=0;i<5/*however many maps we have*/;i++) ticks.add(null);
        worlds=new ArrayList<Image>();
        for(int i=0;i<5/*however many maps we have*/;i++) worlds.add(null);
        
        loadImage();
        
        // initialize the game state
        directionKey = -1;
        e=false;
        fireE=false;
        optionKey=0;
        o1=false;
        o2=false;

        //player and camera and farm
        Player.create();
        camera = new Point();
        Farm.create();

        //test wit two plants
       //for(int i=0;i<12;i++) Player.get().addToInventory(new Plant());
        
        // test map
        resetMap(0);
        scene=0;
       // text = new TextAnimator("i love ap csa :)");
        // farm=new Farm(1,1);
        // jswing stuff again
        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void resetMap(int map) {
        ArrayList<Displayable> dmap=new ArrayList<Displayable>();
        ArrayList<Tickable> t=new ArrayList<Tickable>();
        switch (map) {
            case 0:
                t.add(new Hitbox(new Point(-950,-20),200,300));
                t.add(new Hitbox(new Point(-800,-50),400,300));
                t.add(new Hitbox(new Point(-25,-40),340,330));
                t.add(new Hitbox(new Point(890,-45),480,280));
                Door garden=new Door(new Collider(new Point(-25,150),100,50),1);
                t.add(garden);
                t.add(new Door(new Collider(new Point(-950,170),100,50),3));
                t.add(new Door(new Collider(new Point(890,140),100,50),2));
                break;
            case 1:
                t.add(new Door(new Collider(new Point(-360,380),75,100),0));
                t.add(Farm.get());
                break;
            case 2:
                t.add(new Door(new Collider(new Point(0,245),100,50),0));
                break;
            case 3:
                t.add(new Door(new Collider(new Point(-155,245),100,50),0));
                break;
        }
        t.add(Player.get());
        dmap.add(Player.get());
        displays.set(map,dmap);
        ticks.set(map,t);

    }
    private void resetPlayerPos(int map) {
        switch(map) {
            case 0:
            Player.get().getPos().x=0;
            Player.get().getPos().y=215;
            break;
            case 2:
                Player.get().getPos().x=0;
                Player.get().getPos().y=230;
                break;
            default:
                Player.get().getPos().x=0;
                Player.get().getPos().y=0;
                break;
        }
    }
    public void toScene(int scene) {
        this.scene=scene;
        resetPlayerPos(scene);
        if(displays.get(scene)==null) resetMap(scene);
    }
    private void loadImage() {
        try {
            Image world = ImageIO.read(new File("images/Main_Ref_Image.png"));
            Image garden=ImageIO.read(new File("images/Garden_Foreground.png"));
            worlds.set(0, world);
            worlds.set(1,garden);
            worlds.set(2,ImageIO.read(new File("images/Library_Ref_Image.png")));
            worlds.set(3,ImageIO.read(new File("images/Home_Ref_Image.png")));
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
        camera.x = Math.max(-Math.abs(getWidth() - worlds.get(scene).getWidth(this)) / 2,
                Math.min(camera.x, Math.abs(getWidth() - worlds.get(scene).getWidth(this)) / 2));
        camera.y = Math.max(-Math.abs(getHeight() - worlds.get(scene).getHeight(this)) / 2,
                Math.min(camera.y, Math.abs(getHeight() - worlds.get(scene).getHeight(this)) / 2));

        // tick tok
        ArrayList<Tickable> tick=ticks.get(scene);
        for(Tickable t:tick) t.tick();
        NPC.dialogueHandler();

        // prevent player from moving out of bounds
        Player.get().getPos().x = Math.max(-(worlds.get(scene).getWidth(this) / 2 - 25),
                Math.min(worlds.get(scene).getWidth(this) / 2 - 25, Player.get().getPos().x));
        Player.get().getPos().y = Math.max(-(worlds.get(scene).getHeight(this) / 2 - 25),
                Math.min(worlds.get(scene).getHeight(this) / 2 - 25, Player.get().getPos().y));
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
        Displayable.drawAll(g,displays.get(scene));
        NPC.displayDialogue(g);
        //if farm draw farm
        //kinda clunky code tbh but its 10pm so idc
        if(scene==1) Farm.get().draw(g);
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
        if(key==KeyEvent.VK_3) TestChess.main(new String[]{"1"});
        if(key==KeyEvent.VK_4) System.out.println(Player.get().getPos());
    }

    // drawing
    private void drawBackground(Graphics g) {
        g.drawImage(worlds.get(scene),
                0 - worlds.get(scene).getWidth(this) / 2 - camera.x + getWidth() / 2,
                0 - worlds.get(scene).getHeight(this) / 2 - camera.y + getHeight() / 2,
                this);
    }

    // random camera getter, probably useless and should be deleted soon!
    public Point camera() {
        return camera;
    }
    // public Farm getFarm() {return null;}
}
