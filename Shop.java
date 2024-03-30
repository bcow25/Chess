public class Shop {
    private Plant[] items;
    //private Player player;
    public Shop(Game game)
    {
        //player = new Player(game);
        items = new Plant[10];
        //https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html
        //replace with a hashtable???
        items[0] = new Plant("rose", 2, 2);
        items[1] = new Plant("sunflower", 3, 2);
        items[2] = new Plant("marigold", 5, 2);
        items[3] = new Plant("bush", 10, 2);
        items[4] = new Plant("orchid", 15, 2);
        items[5] = new Plant("balloon flower", 20, 1);
        items[6] = new Plant("peony", 25, 1);
        items[7] = new Plant("lily", 25, 1);
        items[8] = new Plant("bird flower", 30, 0);
        items[9] = new Plant("hydrangea", 30, 0);
    }
    /**
     * return price of specific piece 
     * return -1 if price not found
     */

    public int getPrice(Plant pla)
    {
        for(Plant p : items){
            if (p.equals(pla)) return p.getPrice(); 
        }
        return -1; 
    }
    /**
     * update player's coin count
     * put item into player's inventory
     * if unable to buy, print why not
     */
    public void buy(Plant obj)
    {
        if (Player.get().getNumCoins() < getPrice(obj))
            System.out.println("You do not have enough coins to buy this item.");
        else if (Player.getInventory().size() == 9) System.out.println("Your inventory is full, please plant your plants or discard them"); 
        else
        {
            Player.get().addToInventory( obj);
            Player.get().changeNumCoins(getPrice(obj) * -1);
        }
    }
}
