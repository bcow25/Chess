public abstract class Character extends Animatable {
    protected Animator walkL;
    protected Animator walkU;
    protected Animator walkD;
    protected Animator walkR;
    protected Animator idle;
    public void idle() {
        idle.reset();
        current=idle;
    }
    public void walkL() {
        walkL.reset();
        current=walkL;
    }
    public void walkU() {
        walkU.reset();
        current=walkU;
    }
    public void walkD() {
        walkD.reset();
        current=walkD;
    }
    public void walkR() {
        walkR.reset();
        current=walkR;
    }
}
