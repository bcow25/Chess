import java.awt.Image;

public class Plant {
    
    private String name; 
    @SuppressWarnings("unused")
    private int rarity; //0 is epic, 1 is rare, 2 is common
    private boolean seed; //true -> is a seed, false -> grown
    private static String[] types={"Balloon","Bird","Bush","Hydrangea","Marigold","Orchid","Peony","Rose","Sunflower"};
    private static int[] raritys={1,0,2,0,2,2,1,2,2};
    private static Image[] images=new Image[raritys.length];
    
    
    public Plant(int i) {
        name = types[i]; 
        rarity=raritys[i];
        seed=false;
    }
    public Plant() {
        this((int)(Math.random()*types.length));
    }
    
    public String getName(){return name; } 
    
    public boolean equals(Plant p){
        return(p.getName().equals(name)); 
    }
    
}
