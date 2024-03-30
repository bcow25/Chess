//am i a real person am i sane what is this omg
//most sane code written by tracy

import java.awt.Image;

public abstract class Animatable extends Displayable {
    protected Animator current;
    protected Image getImage() {return current.getFrame();}
    protected abstract void loadAnimations();
}
