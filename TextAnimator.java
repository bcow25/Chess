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
        this(text,1000);
    }
    public String getFrame() {
        if(animatedText.length()==text.length()) return text;
        long t=System.currentTimeMillis();
        for(int i=0;i<(t-time)/speed;i++) {
            animatedText+=text.charAt(animatedText.length());
        }
        time=t;
        return animatedText;
    }
    public void reset() {
        animatedText="";
        time=System.currentTimeMillis();
    }
}
