package Flyweight;

import java.util.ArrayList;
import java.util.List;

class FormattedText {
    private String plaintext;

    public FormattedText(String plaintext) {
        this.plaintext = plaintext;
    }

    public void capitalize(int start, int end) {

    }

    @Override
    public String toString() {
        return "e";
    }
}

class BetterFormattedText {

    private String plaintext;
    private List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedText(String plaintext) {
        this.plaintext = plaintext;
    }

    public TextRange getRange(int start, int end) {
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plaintext.length(); ++i) {
            char c = plaintext.charAt(i);
            for (TextRange range : formatting)
                if (range.covers(i) && range.capitalize)
                    c = Character.toUpperCase(c);
            sb.append(c);
        }
        return sb.toString();
    }

    public class TextRange {
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position) {
            return position >= start && position <= end;
        }
    }
}

public class TextFormattingFlyweight {
    public static void main(String[] args) {
        BetterFormattedText text = new BetterFormattedText("Lorem ipsum dolor sit amet");
        text.getRange(6, 10).capitalize = true;
        System.out.println(text);
    }
}
