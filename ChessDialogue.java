import java.util.ArrayList;

public class ChessDialogue extends Dialogue {
    private boolean exe;
    private String d;
    public ChessDialogue(String text,String d) {
        super(text, null);
        exe=false;
        this.d=d;
    }
    public String getText() {
        String t=super.getText();
        if(text.done()&&!exe) {
            TestChess.main(new String[]{d});
            exe=true;
        }
        return t;
    }
    public void reset() {
        super.reset();
        exe=false;
    }

}