public class Farm {
    private static Plant[][] farm;
    private static int numPlants; 
    public Farm(int r, int c)
    {
        farm = new Plant[r][c];
        numPlants = 0; 
    }
    
    /**
     * plants plant at row, col
     */
    public static void plant (Plant plant, int row, int col)
    {
        farm[row][col] = plant;
        if(farm[row][col]!=null) 
            numPlants++;
        else System.out.println("Warning: attempt to replant in non-empty slot");
        
    }
    
    
    public static Plant[][] getFarm(){ return farm;} 
    public static int getNumPlants(){return numPlants;}
    public static void changeNumPlants(int n) {numPlants += n;}
    
    /**
     * remove plant at row, col
     * return plant to inventory
     */
    public static void removePlant (int row, int col)
    {
        if(farm[row][col] != null){
            Player.get().addToInventory(farm[row][col]); 
            farm[row][col] = null;
            numPlants--;
        } else System.out.println("there's no plant here");
    }
}
