package Factory;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory {

    private int numberOfPeople = 0;

    public Person createPerson(String name) {
        Person p = new Person(numberOfPeople, name);
        ++numberOfPeople;
        return p;
    }
}

public class FactoryExercise {
    public static void main(String[] args) {
        PersonFactory pf = new PersonFactory();
        Person olek = pf.createPerson("Olek");
        Person marysia = pf.createPerson("Marysia");
        System.out.println(marysia.id);
        System.out.println(olek.id);
    }
}
