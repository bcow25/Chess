import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plant extends Displayable {
    
    @SuppressWarnings("unused")
    //rarity; //0 is epic, 1 is rare, 2 is common
    private boolean seed;
    private final static String[] types={"Balloon","Bird","Bush","Hydrangea","Marigold","Orchid","Peony","Rose","Sunflower"};
    private final static int[] raritys={1,0,2,0,2,2,1,2,2};
    private static final String[] rarities={"Legendary","Rare","Common"};
    private final static Image[] images=new Image[raritys.length];
    private static Image seedimg;
    private int id;
    private long time;
    
    public Plant(int i) {
        super(new Point(),0,0,0,0);
        id=i;
        if(images[i]==null) {
            try {
                images[i]=ImageIO.read(new File("images/Plants/"+types[i]+".png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(seedimg==null) {
            try {
                seedimg=ImageIO.read(new File("images/Plants/Seeds.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        seed=true;
    }
    public Plant() {
        this((int)(Math.random()*types.length));
    }
    public void plot() {
        pos.x=Farm.get().getFarm().size()%8*50-190;
        pos.y=(Farm.get().getFarm().size()/8)*50-360;
        time=System.currentTimeMillis();
        //System.out.println("hello???");

    }
    public String getName(){return types[id]; } 
    @Override
    protected Image getImage() {
        if(!seed)
        return images[id];
        else return seedimg;
    }
    public Point getPos() {
        return pos;
    }
    public void tick() {
        if(System.currentTimeMillis()-time>5000) {
            if(seed) new Messager("You got a "+types[id]+" Plant ("+rarities[raritys[id]]+")!");
            seed=false;
        }
    }
    
}
