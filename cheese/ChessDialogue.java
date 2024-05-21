import java.util.ArrayList;

public class ChessDialogue extends Dialogue {
    private boolean exe;
    public ChessDialogue(String text) {
        super(text, null);
        exe=false;
    }
    public String getText() {
        String t=super.getText();
        if(text.done()&&!exe) {
            TestChess.main(new String[]{"0"});
            exe=true;
        }
        return t;
    }
    public void reset() {
        super.reset();
        exe=false;
    }

}