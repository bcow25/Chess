public class TextAnimator {
    private String text;
    private String animatedText;
    private int speed;
    private long time;
    public TextAnimator(String text, int speed) {
        this.text=text;
        this.speed=speed;
        animatedText="";
        time=0;
    }
    public TextAnimator(String text) {
        this(text,50);
    }
    public void reset() {
        animatedText="";
        time=System.currentTimeMillis();
    }
}
