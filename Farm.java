public class Farm {
    private static Plant[][] farm;
    private static int numPlants; 
    public Farm(int r, int c)
    {
        farm = new Plant[r][c];
        numPlants = 0; 
    }
    
    public static Plant[][] getFarm(){ return farm;} 
    public static int getNumPlants(){return numPlants;}
    public static void changeNumPlants(int n) {numPlants += n;}
    
     //remove plant from inventory and plant it at row r and col c
    public void plant(Plant plant, int r, int c){
        if (Player.getInventory().contains(plant) && r < farm.length && c < farm[0].length && numPlants < 12){
            farm[r][c] = Player.removeFromInventory(plant); 
            numPlants ++; 
        } else if (!(r < farm.length && c <farm[0].length)) 
            System.out.println("the farm isn't that big, pick somewhere else");
        else if (numPlants == 12)
            System.out.println("farm is full :("); 
        else if (!Player.getInventory().contains(plant))
            System.out.println("You dont have this plant :'(");
        else System.out.println("panic: we shouldn't be here: Farm method plant"); 
    }
    
    
    /**
     * remove plant at row, col
     * return plant to inventory
     */
    public static void removePlant (int row, int col)
    {
        if(farm[row][col] != null){
            Player.get().addToInventory(farm[row][col]); 
            farm[row][col] = null;
            numPlants --; 
            
        } else System.out.println("there's no plant here");
    }
}
