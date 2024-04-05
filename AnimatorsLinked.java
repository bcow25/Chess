import java.awt.Image;
public class AnimatorsLinked extends Animator {
    private AnimatorsLinked next;
    public AnimatorsLinked(Image[] frames, AnimatorsLinked next) {
        super(frames);
        this.next=next;
    }
    public AnimatorsLinked(Animator a) {
        super(a.frames, a.speed);
        next=null;
    }
    public AnimatorsLinked(Animator a, Animator b) {
        this(a);
        next=new AnimatorsLinked(b);
    }
    public Image getFrame() {
        if(!(next==null||System.currentTimeMillis()-time<speed*frames.length)) {
        frames=next.frames;
        reset();
        speed=next.speed;
        next=next.next; //what am i doing?
    }
        return super.getFrame();
    }
}