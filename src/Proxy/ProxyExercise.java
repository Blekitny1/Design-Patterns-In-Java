package Proxy;

class Person2
{
    private int age;

    public Person2(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String drink() { return "drinking"; }
    public String drive() { return "driving"; }
    public String drinkAndDrive() { return "driving while drunk"; }
}

class ResponsiblePerson extends Person2
{
    public ResponsiblePerson(int age) {
        super(age);
    }

    @Override
    public String drink() {
        return this.getAge() >= 18 ? super.drink() : "too young";
    }

    @Override
    public String drive() {
        return this.getAge() >= 16 ? super.drive() : "too young"; // more elegant way of if(){}else{}
    }

    @Override
    public String drinkAndDrive(){
        return "dead";
    }
}

public class ProxyExercise {
    public static void main(String[] args) {
        Person2 person2 = new Person2(22);
        System.out.println(person2.drive());
        ResponsiblePerson responsiblePerson = new ResponsiblePerson(15); // same usage as person
        ResponsiblePerson responsiblePerson2 = new ResponsiblePerson(17);
        ResponsiblePerson responsiblePerson3 = new ResponsiblePerson(19);
        System.out.println(responsiblePerson.drive());
        System.out.println(responsiblePerson2.drive());
        System.out.println(responsiblePerson3.drinkAndDrive());
    }
}
