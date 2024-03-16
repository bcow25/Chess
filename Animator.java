import java.awt.Image;
public class Animator {
    private Image[] frames;
    private int speed; //time before new frame i guess
    private long time;
    public Animator(Image[] frames) {
        this.frames=frames;
        speed=100;
        reset();
    }
    public Animator(Image[] frames, int speed) {
        this(frames);
        this.speed=speed;
    }
    public Image getFrame() {
        return frames[((int)(System.currentTimeMillis()-time)/speed)%frames.length];
    }
    public void reset() {
        time=System.currentTimeMillis();
    }
}
