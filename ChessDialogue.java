public class ChessDialogue extends Dialogue {
    private int d;
    public ChessDialogue(String text,int d) {
        super(text, null);
        this.d=d;
    }
    public String getText() {
        String t=super.getText();
        System.out.println("why is life hell");
        if(text.done()) {
            ChessPlayer.create(d);
            System.out.println("oease");
            Game.get().toScene(4);
            NPC.clearCurrent();
        }
        return t;
    }
    public void reset() {
        super.reset();
    }

}