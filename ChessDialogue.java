public class ChessDialogue extends Dialogue {
    private String d;
    public ChessDialogue(String text,String d) {
        super(text, null);
        this.d=d;
    }
    public String getText() {
        String t=super.getText();
        if(text.done()) {
            Game.get().toScene(4);
            NPC.clearCurrent();
        }
        return t;
    }
    public void reset() {
        super.reset();
    }

}