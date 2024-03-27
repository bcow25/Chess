import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Player extends Character {

    private int xvel;
    private int yvel;
    private int numCoins;
    private static  ArrayList<Plant> inventory;
    public Player(Game game) {
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

    public ArrayList<Plant> getInventory(){ return inventory;}
    public int getNumCoins() {return numCoins;}
    public static void addToInventory(Plant plant) {inventory.add(plant); }
    
    //remove plant from inventory and return it if found
    //if not print error messages
    public static Plant removeFromInventory(Plant plant){ 
        Plant temp = new Plant(/*fill this in with plant constructor*/); 
        if(inventory.contains(plant)){
            inventory.remove(plant); 
            return temp; 
        } else {
            System.out.println("You don't have this plant in your inventory");
            return null; 
        }
    }
    
    //remove plant from inventory and plant it at row r and col c
    public void plant(Plant plant, int r, int c){
        if (inventory.contains(plant) && r < Farm.getFarm().length && c < Farm.getFarm()[0].length){
            Farm.plant(removeFromInventory(plant), r, c); 
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
