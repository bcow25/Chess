import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Player extends Character {
    // singleton stuff
    private static Player p = null;

    public static void create() {
        if (p == null)
            p = new Player();
        else
            System.out.println("Warning: reinstantiating player");
    }

    public static Player get() {
        return p;
    }

    @SuppressWarnings("unused")
    // note: encapsulate these later
    public int xvel;
    public int yvel;
    // inventory stuff wth
    private static int numCoins;
    private static ArrayList<Plant> inventory;
    // footprint stuff
    private ArrayList<FootPrint> footprintSpots;
    private Image fp;
    // prob should be replaced
    private int numTicks;

    private class FootPrint {
        private char direction;
        private int opacity;
        private Point pos;

        public FootPrint(Point pos, int opacity, char direction) {
            this.pos = pos;
            this.opacity = opacity;
            this.direction = direction;
        }

        public char getDirection() {
            return direction;
        }

        public int getOpacity() {
            return opacity;
        }

        public Point getPos() {
            return pos;
        }

        public void changeOpacity() {
            opacity -= 10;
        }
        public String toString() {
            return "pos: "+pos+", opacity: "+opacity+", direction: "+direction;
        }
    }

    private Player() {
        super("Riley",new Point(),50,90,50,90);
        xvel = 0;
        yvel = 0;
        // initialize the state
        
        //collider = new Collider(pos, 50, 50);
        inventory = new ArrayList<Plant>();
        numCoins = 0;

        try {
            fp = ImageIO.read(new File("images/shoeprint.png")).getScaledInstance(1, 1, Image.SCALE_DEFAULT);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
        footprintSpots = new ArrayList<FootPrint>();
    }

    // getters and setters
    public Collider getCollider() {
        return collider;
    }

    public ArrayList<Plant> getInventory() {
        return inventory;
    }

    public static int getNumCoins() {
        return numCoins;
    }

    public static void changeNumCoins(int c) {
        numCoins += c;
    }

    public static void addToInventory(Plant plant) {
        if (inventory.size() < 9)
            inventory.add(plant);
        else
            System.out.println("inventory is full");
    }

    // inventory stuff
    // remove plant from inventory and return it if found
    // if not print error messages
    public Plant removeFromInventory(Plant plant) {
        Plant temp = null; // = new Plant(/*fill this in with plant constructor*/); //sorry the compiler
                           // error is killing me
        if (inventory.contains(plant)) {
            inventory.remove(plant);
            return temp;
        } else {
            System.out.println("You don't have this plant in your inventory");
            return null;
        }
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.
        numTicks++;
        if (Game.get().getDirectionKey() == KeyEvent.VK_UP)
            yvel = -10;
        if (Game.get().getDirectionKey() == KeyEvent.VK_DOWN)
            yvel = 10;
        if (Game.get().getDirectionKey() == KeyEvent.VK_LEFT)
            xvel = -10;
        if (Game.get().getDirectionKey() == KeyEvent.VK_RIGHT)
            xvel = 10;
        createFootprint();

        pos.x += xvel;
        pos.y += yvel;
        xvel *= 0.9;
        yvel *= 0.9;
        if(xvel==0&&yvel==0&&current!=idle) idle();
    }

    public void createFootprint() {
        if (numTicks % 4 == 0 && Math.abs(xvel) + Math.abs(yvel) > 1) // increments footprints by 1.5 sec
        {
            char d = 'N';
            switch (Game.get().getDirectionKey()) {
                case KeyEvent.VK_DOWN:
                    d = 'S';
                    break;
                case KeyEvent.VK_LEFT:
                    d = 'W';
                    break;
                case KeyEvent.VK_RIGHT:
                    d = 'E';
                    break;
                case -1:
                    if (footprintSpots.size() == 0)
                        return;
                    d = footprintSpots.get(footprintSpots.size() - 1).getDirection();
                    break;
            }
            footprintSpots.add(new FootPrint(new Point(pos), 100, d));
            for (int i = 0; i < footprintSpots.size(); i++)
                footprintSpots.get(i).changeOpacity();
            if (footprintSpots.get(0).getOpacity() <= 0)
                footprintSpots.remove(0);
            //System.out.println(footprintSpots);
        }
    }

    public Point getPos() {
        return pos;
    }
}
