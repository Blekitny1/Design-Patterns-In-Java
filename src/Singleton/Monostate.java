package Singleton;

class ChiefExecutiveOfficer {
    private static String name; // we can generate many instances, but they all share same storage elements
    private static int age; // because of static!

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public void setAge(int age) {
        ChiefExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return "ChiefExecutiveOfficer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Monostate {

    public static void main(String[] args) {
        ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
        ceo.setName("Janusz Filipak");
        ceo.setAge(42);

        ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
        System.out.println(ceo2); //this is rather confusing, since ceo2 does not communicate with ceo, nor
        // it doesn't immediately suggest that it is a singleton. Also unsafe memorywise.
    }
}
