package Builder;

class Person {
    public String name;
    public String position;

    @Override
    public String toString(){
        return "Builder.Person{ " + "name= " + name + '\'' + ", position='" + position + '\'' + '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>>{ //to preserve fluent interface we need recursive generics
    protected Person person = new Person();
    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    public Person build(){
        return person;
    }

    protected SELF self() { //in the most derived type that we use
        return (SELF) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> { //we use this type argument here
    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class FluentBuilderWithRecursiveGenerics {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder();
        Person olek = eb
                .withName("Olek") //we can't call worksAt, because .withName() returns only Builder.PersonBuilder
                .worksAt("Developer")
                .build();
        System.out.println(olek);
    }
}
