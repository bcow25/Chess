import java.util.ArrayList;

public class PlayerNew {
    private int numCoins;
    private ArrayList<ArrayList<Object>> inventory;
    public PlayerNew()
    {
        inventory = new ArrayList<ArrayList<Object>>();
        for (int i = 0; i < inventory.size(); i++)
            inventory.get(i).set(1, 0);
        
    }
    public ArrayList<ArrayList<Object>> getInventory()
    {
        return inventory;
    }
    public int getNumCoins;
    public void addToInventory(Object obj)
    {
        
    }
    public Object removeFromInventory(Object obj)
    {
        return null; //so it compiles
    }
}
