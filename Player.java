import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Player extends Character {
    private static Player p=null;
    private int xvel;
    private int yvel;
    private int numCoins;
    private ArrayList<Plant> inventory;
    private Player(Game game) {
        this.game=game;
        // load the assets
        loadAnimations();
        xvel=0;
        yvel=0;
        // initialize the state
        pos = new Point();

        inventory = new ArrayList<Plant>();
        numCoins = 0; 
    }
    public static void create(Game g) {
        if(p==null)
        p=new Player(g);
        else System.out.println("Warning: reinstantiating player");
    }
    public static Player get() {
        return p;
    }

    public ArrayList<Plant> getInventory(){ return p.inventory;}
    public int getNumCoins() {return p.numCoins;}
    public void setNumCoins(int c) {p.numCoins=c;}
    public void addToInventory(Plant plant) {inventory.add(plant); }
    
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
    
    //remove plant from inventory and plant it at row r and col c
    public static void plant(Plant plant, int r, int c){
        if (p.inventory.contains(plant) && r < Farm.getFarm().length && c < Farm.getFarm()[0].length){
            Farm.plant(p.removeFromInventory(plant), r, c); 
        } else if (!(r < Farm.getFarm().length && c < Farm.getFarm()[0].length)) System.out.println("the farm isn't that big, pick somewhere else");
        else System.out.println("You dont have this plant :'(");
    }

    //temp
    private void loadAnimations() {
        try {

            Image killme = ImageIO.read(new File("images/player.jpg")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
            while(killme==null); //most sane code written by tracy
            Image[] suicide=new Image[1];
            suicide[0]=killme;
            current=new Animator(suicide);
            walkD=walkL=walkR=walkU=current; //i deserve the death sentence
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
    

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.
        if(game.up) yvel=-10;
        if(game.down) yvel=10;
        if(game.left) xvel=-10;
        if(game.right) xvel=10;

        pos.x+=xvel;
        pos.y+=yvel;
        xvel*=0.9;
        yvel*=0.9;
    }

    public Point getPos() {
        return pos;
    }

}
