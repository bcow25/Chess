public class Option {
        private String str;
        private Dialogue dialogue;
        public Option(String str, Dialogue dialogue) {
            this.str=str;
            this.dialogue=dialogue;
        }
        public String getString() {
            return str;
        }
        public Dialogue getDialogue() {
            return dialogue;
        }
}
