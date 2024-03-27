public class Plant {
    private int price;
    private String name; 
    private int rarity; //0 is epic, 1 is rare, 2 is common
    
    
    public Plant(String type, int p, int r)
    {
        price = p; 
        name = type; 
        rarity = r; 
    }
    
    public int getPrice(){return price;}
    public String getName(){return name; } 
    
    public boolean equals(Plant p){
        return(p.getName().equals(name)); 
    }
    
}
