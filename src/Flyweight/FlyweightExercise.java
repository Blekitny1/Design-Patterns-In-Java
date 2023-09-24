package Flyweight;

import java.util.*;

class Sentence
{
    public ArrayList<WordToken> split;

    public Sentence(String plainText)
    {
        String[] strings = plainText.split(" ");
        this.split = new ArrayList<>();
        for (String s : strings) {
            this.split.add(new WordToken(s));
        }

        //this.split = Arrays.stream(plainText.split(" ")).map(s -> new WordToken(s));
    }

    public WordToken getWord(int index)
    {
        return split.get(index);

    }

    @Override
    public String toString()
    {
        ArrayList<String> strings = new ArrayList<>();
        for (WordToken wt : this.split) {
            if (wt.capitalize)
                wt.text = wt.text.toUpperCase();
            strings.add(wt.text);
        }
        return String.join(" ", strings);
    }

    class WordToken
    {
        public String text;

        public WordToken(String text) {
            this.text = text;
        }

        public boolean capitalize;
    }
}

public class FlyweightExercise {
    public static void main(String[] args) {
        Sentence sentence = new Sentence("hello world");
        sentence.getWord(1).capitalize = true;
        System.out.println(sentence);
    }
}
