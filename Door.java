public class Door implements Tickable {
    private Collider collider;
    private int scene;
    public Door(Collider collider,int scene) {
        this.collider=collider;
        this.scene=scene;
    }
    public void tick() {
        if(Game.get().getE()&&collider.isColliding(Player.get().getCollider())) {
            Game.get().setE();
            Game.get().toScene(scene);
            
        }
    }
    
}
