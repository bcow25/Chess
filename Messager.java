import java.util.Iterator;
import java.util.LinkedList;
import java.awt.*;

public class Messager {
    private static LinkedList<Messager> messagers = new LinkedList<Messager>();
    private static final int fade = 10;
    private long time;
    private String text;
    private int duration; // in miliseconds, time before starting to fade
    private int opacity; // 255 to 0

    public Messager(String text, int duration) {
        messagers.add(this);
        time = System.currentTimeMillis();
        this.duration = duration;
    }

    public Messager(String text) {
        this(text, 3000);
    }

    private boolean done() {
        return System.currentTimeMillis() - time > duration;
    }

    private void changeOpacity() {
        opacity -= fade;
    }

    public static void tick() {
        Iterator<Messager> iterator = messagers.iterator();
        while (iterator.hasNext()) {
            Messager m = iterator.next();
            if (m.done())
                m.changeOpacity();
            if (m.opacity <= 0)
                iterator.remove();
        }
    }

    public static void drawAll(Graphics g) {
        Iterator<Messager> iterator = messagers.iterator();
        for (int y=0;iterator.hasNext();y++) {
            Messager m = iterator.next();
            g.setColor(new Color(255, 255, 255, m.opacity));
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setFont(new Font("Lato", Font.BOLD, 20));
            g2d.drawString(m.text, -200, 10+y*20);
        }
    }
    public static void clear() {
        messagers.clear();
    }
}
