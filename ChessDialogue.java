public class ChessDialogue extends Dialogue {
    private int d;
    public ChessDialogue(String text,int d) {
        super(text, null);
        this.d=d;
    }
    public String getText() {
        String t=super.getText();
        if(text.done()) {
            ChessPlayer.create(d);
            Game.get().toScene(4);
            NPC.clearCurrent();
        }
        return t;
    }
    public void reset() {
        super.reset();
    }

}