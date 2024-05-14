import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;
import java.awt.*;
public class TextDisplayer {
    private static Stack<TextDisplayer> displayers=new Stack<TextDisplayer>();
    private Point pos;
    private String text;
    private int height;
    private int width;
    public TextDisplayer(Point pos,int width, int height,String text) {
        if(displayers==null) displayers=new Stack<TextDisplayer>();
        displayers.push(this);
        this.pos=pos;
        this.text=text;
        this.width=width;
        this.height=height;
    }
    private void draw(Graphics g, Point pos) {
        g.setColor(new Color(214, 214, 214));
        g.fillRect( pos.x, 
                        pos.y, 
                      width, height);
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
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
     g2d.drawString(text, pos.x, pos.y);
        }
    private void draw(Graphics g) {
            draw(g, pos);
        }
    public static void drawAll(Graphics g) {
        for(TextDisplayer t:displayers)
            t.draw(g);
    }
    public static void tick() {
        if(Game.get().getE()&&!displayers.empty()) {
            displayers.pop();
            Game.get().setE();
        }
    }
}

