public class TextAnimator {
    private String text;
    private String animatedText;
    private int speed;
    private long time;
    public TextAnimator(String text, int speed) {
        this.text=text;
        this.speed=speed;
        reset();
        time=0;
    }
    public TextAnimator(String text) {
        this(text,50);
    }
    public String getFrame() {
        if(done()) return text;
        long t=System.currentTimeMillis();
        if (t-time>speed) {
            //System.out.println("pain pain pain");
            animatedText+=text.charAt(animatedText.length());
            time=t;
        }
        return animatedText;
    }
    public void reset() {
        animatedText="";
        time=System.currentTimeMillis();
    }
    private boolean done() {
        return animatedText.length()==text.length();
    }
}
