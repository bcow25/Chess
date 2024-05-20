//am i a real person am i sane what is this omg
//most sane code written by tracy

import java.awt.Image;
import java.awt.Point;

public abstract class Animatable extends Displayable {
    public Animatable(Point pos, int dW, int dH, int cW, int cH) {
        super(pos, dW, dH, cW, cH);
        //TODO Auto-generated constructor stub
    }
    protected Animator current;
    protected Image getImage() {return current.getFrame();}
    protected abstract void loadAnimations();
}
