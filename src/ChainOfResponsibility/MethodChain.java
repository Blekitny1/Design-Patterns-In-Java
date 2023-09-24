package ChainOfResponsibility;

class Creature {
    public String name;
    public int attack, defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}

class CreatureModifier {
    protected Creature creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier cm) {
        if (next != null) {
            next.add(cm);
        }
            else next = cm;
    }

    public void handle(){
        if (next != null) next.handle();
    }
}

class DoubleAttackModifier extends CreatureModifier {
    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        creature.attack *= 2;
        System.out.println("Doubling " + creature.name + "'s attack");
        super.handle();
    }
}

class IncreaseDefenseModifier extends CreatureModifier {
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        creature.defense += 3;
        System.out.println("Increasing " + creature.name + "'s defense");
        super.handle();
    }
}

class NoBonusesModifier extends CreatureModifier {
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {System.out.println("No bonuses for " + creature.name);}
}

public class MethodChain {
    public static void main(String[] args) {
        Creature oger = new Creature("Oger", 6, 7);
        System.out.println(oger);

        CreatureModifier root = new CreatureModifier(oger);

        root.add(new DoubleAttackModifier(oger));
        root.add(new NoBonusesModifier(oger));
        root.add(new IncreaseDefenseModifier(oger));
        root.handle();

        System.out.println(oger);

    }
}
