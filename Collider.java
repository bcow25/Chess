import java.awt.Point;
public class Collider {
    private Point pos;
    private int w;
    public int getW() {
        return w;
    }
    private int h;
    public int getH() {
        return h;
    }
    public Collider(Point pos,int w,int h) {
        this.pos=pos;
        this.w=w;
        this.h=h;
    }
    public Collider(Point pos) {
        this(pos,0,0);
    }
    public boolean isColliding(Collider c) {
        return Math.abs(c.pos.x-pos.x)<(w+c.w)/2&&Math.abs(c.pos.y-pos.y)<(h+c.h)/2;
    } 
    public void pushPlayer() {
        if(isColliding(Player.get().getCollider())) {
            if(Math.abs(Player.get().xvel)>Math.abs(Player.get().yvel)){
            if(Player.get().xvel!=0) Player.get().getPos().x=pos.x-Math.abs(Player.get().xvel)/Player.get().xvel*(getW()+Player.get().getCollider().getW()+5)/2;
            Player.get().xvel=0;
            } else {
            if(Player.get().yvel!=0) Player.get().getPos().y=pos.y-Math.abs(Player.get().yvel)/Player.get().yvel*(getH()+Player.get().getCollider().getH()+5)/2; 
                Player.get().yvel=0;
        }            
        }
    }
}