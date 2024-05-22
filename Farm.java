import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Farm implements Tickable {
    public static void create() {
        f=new Farm();
    }
    public static Farm get() {
        return f;
    }
    private static Farm f;
    private ArrayList<Plant> farm;
    private final int maxSize; 
    private Collider collider;
    public Farm()
    {
        maxSize=64;
        farm=new ArrayList<Plant>(maxSize);
        collider=new Collider(new Point(0,-250),500,500);
    }
    
    public ArrayList<Plant> getFarm(){ return farm;} 
    public int getNumPlants(){return farm.size();}
    
    public void plant(Plant plant){
        if (Player.get().getInventory().contains(plant) && farm.size()<maxSize){
            farm.add(plant);
            Player.get().removeFromInventory(plant);
            System.out.println(plant.getName());
            System.out.println(farm);
        } 
    }
    @Override
    public void tick() {
        if(Game.get().getE()&&collider.isColliding(Player.get().getCollider())) {
            Game.get().setE();
            if(Player.get().getInventory().size()!=0)
            plant(Player.get().getInventory().get(0));
        }
    }
    public void draw(Graphics g) {
        for(Plant p:farm) if(p!=null) p.draw(g);
        //im losing it:)
    }

}
