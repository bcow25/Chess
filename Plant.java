public class Plant {
    
    private String name; 
    @SuppressWarnings("unused")
    private int rarity; //0 is epic, 1 is rare, 2 is common
    private int ind; 
    
    public Plant(String type, int r)
    {
        name = type; 
        rarity = r; 
    }
    
    public String getName(){return name; } 
    
    public boolean equals(Plant p){
        return(p.getName().equals(name)); 
    }
    
}
