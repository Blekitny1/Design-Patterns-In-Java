package ChainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

abstract class Creature2
{
    protected Game2 game;
    protected int baseAttack, baseDefense;

    public Creature2(Game2 game, int baseAttack, int baseDefense)
    {
        this.game = game; // every creature must have a game field
        this.baseAttack = baseAttack; // and its base attack and defense values
        this.baseDefense = baseDefense; // to be increased by handling each creature in the game on getStatistic call
    }

    public abstract int getAttack();
    public abstract int getDefense();
    public abstract void query(Object source, StatQuery sq);
}

class Goblin extends Creature2
{
    protected Goblin(Game2 game, int baseAttack, int baseDefense)
    {
        super(game, baseAttack, baseDefense);
    }

    public Goblin(Game2 game)
    {
        super(game, 1, 1);
    }

    @Override
    public int getAttack()
    {
        StatQuery q = new StatQuery(Statistic.ATTACK);
        for (Creature2 c : game.creatures)
            c.query(this, q);
        return q.result;
    }

    @Override
    public int getDefense()
    {
        StatQuery q = new StatQuery(Statistic.DEFENSE);
        for (Creature2 c : game.creatures)
            c.query(this, q);
        return q.result;
    }

    @Override
    public void query(Object source, StatQuery sq)
    {
        if (source == this)
        {
            switch (sq.statistic)
            {
                case ATTACK:
                    sq.result += baseAttack; // we calculate a goblins statistic, so we have to add base value
                    break;
                case DEFENSE:
                    sq.result += baseDefense; // likewise
                    break;
            }
        }
        else if (sq.statistic == Statistic.DEFENSE) // other goblins'
        {
            sq.result++; // increase their defense
        }
    }
}

class GoblinKing extends Goblin
{
    public GoblinKing(Game2 game)
    {
        super(game, 3, 3);
    }

    @Override
    public void query(Object source, StatQuery sq)
    {
        if (source != this && sq.statistic == Statistic.ATTACK) // when querying other goblin's attack give it +1
        {
            sq.result++; // every goblin gets +1 attack
        }
        else super.query(source, sq);
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class StatQuery
{
    public Statistic statistic;
    public int result;

    public StatQuery(Statistic statistic)
    {
        this.statistic = statistic;
    }
}

class Game2
{
    public List<Creature2> creatures = new ArrayList<>();
}