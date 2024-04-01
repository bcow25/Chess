import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
public class Player extends Character {
    private static Player p=null;
    @SuppressWarnings("unused")
    private Collider collider;
    private int xvel;
    private int yvel;
    private int numCoins;
    private ArrayList<Plant> inventory;
    private Player() {
        // load the assets
        loadAnimations();
        xvel = 0;
        yvel = 0;
        // initialize the state
        pos = new Point();
        collider=new Collider(pos,50,50);
        inventory = new ArrayList<Plant>();
        numCoins = 0;
    }
    public static void create() {
        if(p==null)
        p=new Player();
        else System.out.println("Warning: reinstantiating player");
    }
    public static Player get() {
        return p;
    }

    public ArrayList<Plant> getInventory(){ return inventory;}
    public int getNumCoins() {return numCoins;}
    public void changeNumCoins(int c) {numCoins+=c;}
    public void addToInventory(Plant plant) {
        if (inventory.size() < 9)
            inventory.add(plant); 
        else
            System.out.println("inventory is full"); 
    }
    
    //remove plant from inventory and return it if found
    //if not print error messages
    public Plant removeFromInventory(Plant plant){ 
        Plant temp=null; // = new Plant(/*fill this in with plant constructor*/);  //sorry the compiler error is killing me
        if(inventory.contains(plant)){
            inventory.remove(plant); 
            return temp; 
        } else {
            System.out.println("You don't have this plant in your inventory");
            return null; 
        }
    }
    

    // temp
    protected void loadAnimations() {
        try {

            Image killme = ImageIO.read(new File("images/player.jpg")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
            while(killme==null); //most sane code written by tracy
            Image[] suicide=new Image[1];
            suicide[0]=killme;
            current=new Animator(suicide);
            idle=walkD=walkL=walkR=walkU=current; //i deserve the death sentence
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.
        if(Game.get().getLastKeyPressed()==KeyEvent.VK_UP) yvel=-10;
        if(Game.get().getLastKeyPressed()==KeyEvent.VK_DOWN) yvel=10;
        if(Game.get().getLastKeyPressed()==KeyEvent.VK_LEFT) xvel=-10;
        if(Game.get().getLastKeyPressed()==KeyEvent.VK_RIGHT) xvel=10;

        pos.x += xvel;
        pos.y += yvel;
        xvel *= 0.9;
        yvel *= 0.9;
    }

    public Point getPos() {
        return pos;
    }

}
