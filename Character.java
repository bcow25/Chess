import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Character extends Animatable implements Tickable {
    protected Animator walkL;
    protected Animator walkU;
    protected Animator walkD;
    protected Animator walkR;
    protected Animator idle;
    protected String name;

    protected Character(String name,Point pos, int dW,int dH,int cW,int cH) {
        super(pos,dW,dH,cW,cH);
        this.name = name;
        loadAnimations();
    }

    public void idle() {
        idle.reset();
        current = idle;
    }

    public void walkL() {
        walkL.reset();
        current = walkL;
    }

    public void walkU() {
        walkU.reset();
        current = walkU;
    }

    public void walkD() {
        walkD.reset();
        current = walkD;
    }

    public void walkR() {
        walkR.reset();
        current = walkR;
    }
    protected void loadAnimations() {
        String[] load = { "Front", "Back", "Left", "Right" };
        Animator[] animate = new Animator[4];
        
        for (int i = 0; i < 4; i++) {
            try {
                String template="images/" + name + " Sprites/"+load[i]+"/" + name + "_" + load[i];
                Image s = ImageIO.read(new File(template+"_S.png"));

                Image w1 = ImageIO.read(new File(template+"_W1.png"));
                Image w2 = ImageIO.read(new File(template+"_W2.png"));
                Image[] img = new Image[4];
                img[0] = s;
                img[1] = w1;
                img[2] = s;
                img[3] = w2;
                animate[i] = new Animator(img);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        walkD=animate[0];
        walkU=animate[1];
        walkL=animate[2];
        walkR=animate[3];
        try {
            Image pain= ImageIO.read(new File("images/" + name + " Sprites/Front/"+ name + "_Front_S.png"));
            idle=current=new Animator(new Image[]{pain});
            collider.setH(pain.getHeight(Game.get()));
            collider.setW(pain.getWidth(Game.get()));
            display.setW(pain.getWidth(Game.get()));
            display.setH(pain.getHeight(Game.get()));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
