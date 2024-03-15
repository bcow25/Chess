public class Farm {
    private Plant[][] farm;
    public Farm(int r, int c)
    {
        farm = new Plant[r][c];
    }
    
    /**
     * plants plant at row, col
     */
    public void plant (Plant plant, int row, int col)
    {
        farm[row][col] = plant;
    }
    
    /**
     * remove plant at row, col
     * return plant to inventory
     */
    public void removePlant (int row, int col)
    {
        // TO DO: return plant to inventory
        farm[row][col] = null;
    }
}
