import java.awt.Point;
public class Collider {
    private Point pos;
    private int w;
    private int h;
    public Collider(Point pos,int w,int h) {
        this.pos=pos;
        this.w=w;
        this.h=h;
    }
    public boolean isColliding(Collider c) {
        return Math.abs(c.pos.x-pos.x)<(w+c.w)/2&&Math.abs(c.pos.y-pos.y)<(h+c.h)/2;
    } 
}