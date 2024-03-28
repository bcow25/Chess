public class Farm {
    private static Plant[][] farm;
    public Farm(int r, int c)
    {
        farm = new Plant[r][c];
    }
    
    /**
     * plants plant at row, col
     */
    public static void plant (Plant plant, int row, int col)
    {
        farm[row][col] = plant;
    }
    
    
    public static Plant[][] getFarm(){ return farm;} 
    
    /**
     * remove plant at row, col
     * return plant to inventory
     */
    public static void removePlant (int row, int col)
    {
        if(farm[row][col] != null){
            Player.get().addToInventory(farm[row][col]); 
            farm[row][col] = null;
        }

    }
}
