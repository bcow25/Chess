import java.util.List;
public class Dialogue  {
    public class Option {
        private String str;
        private Dialogue dialogue;
        public Option(String str, Dialogue dialogue) {
            this.str=str;
            this.dialogue=dialogue;
        }
        public Dialogue get() {
            return dialogue;
        }
    }
    public List<Option> getOptions() {
        return options;
    }
    private List<Option> options;
    public String getText() {
        return text;
    }
    private String text;
    public Character getSpeaker() {
        return speaker;
    }
    private Character speaker;
    public Dialogue(String text,Character speaker,List<Option> options) {
        this.text=text;
        this.speaker=speaker;
        this.options=options;
    }
}
