package Observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Game
{
    public int count = 0;
    public Map<Integer, Rat> rats = new HashMap<Integer, Rat>();

    public Game(Map<Integer, Rat> rats) {
        this.rats = rats;
    }

    @Override
    public String toString() {
        return "Game{" +
                "count=" + count +
                ", rats=" + rats +
                '}';
    }
}

class Rat implements Closeable
{
    private Game game;
    public int attack = 1;

    public int ratNumber; // this is scuffed variable

    public Rat(Game game)
    {
        // todo: rat enters game here and buffs other rats
        this.game = game;
        ratNumber = game.count++;
        game.rats.put(ratNumber, this);
        for (Rat rat : game.rats.values()) {
            rat.attack = game.rats.size();
        }
    }

    @Override
    public void close() throws IOException
    {
        // todo: rat dies ;( and debuffs other rats
        game.rats.remove(ratNumber);
        for (Rat rat : game.rats.values()) {
            rat.attack = game.rats.size();
        }
    }
}

public class ObserverExercise {

    public static void main(String[] args) throws IOException {
        Game game = new Game(new HashMap<>());
        Rat r1 = new Rat(game);
        Rat r2 = new Rat(game);
        Rat r3 = new Rat(game);
        System.out.println(r1.attack); //3
        System.out.println(r3.attack); //3
        Rat r4 = new Rat(game);
        r2.close();
        r4.close();
        System.out.println(r1.attack); //2
        System.out.println(r3.attack); //2
    }


}
