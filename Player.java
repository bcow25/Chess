import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {

    // image that represents the player's position on the board
    private Game game;
    private Image image;
    // current position of the player on the board grid
    private Point pos;
    // keep track of the player's score
    private int xvel;
    private int yvel;
    public Player(Game game) {
        this.game=game;
        // load the assets
        loadImage();
        xvel=0;
        yvel=0;
        // initialize the state
        pos = new Point();
    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            image = ImageIO.read(new File("images/player.jpg")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
            while(image==null); //most sane code written by tracy
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but 
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        Game b=(Game) observer;
        g.drawImage(
            image, 
            pos.x-b.camera().x+game.getWidth()/2-image.getWidth(game)/2, 
            pos.y-b.camera().y+game.getHeight()/2-image.getHeight(game)/2, 
            observer
        );
    }
    

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
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
