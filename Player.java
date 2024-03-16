import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Character {

    private int xvel;
    private int yvel;
    public Player(Game game) {
        this.game=game;
        // load the assets
        loadAnimations();
        xvel=0;
        yvel=0;
        // initialize the state
        pos = new Point();
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
