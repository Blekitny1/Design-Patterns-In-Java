package Prototype;

import java.util.Arrays;

class Address implements Cloneable{ //Clonable is an interfce that suggest clone method
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{ " + "streetName=" + streetName + '\'' + ", houseNumber= " + houseNumber + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException{ //don't ues clone for prototyping, since it gives shallow copies
        return new Address(streetName, houseNumber);
    }

}

class Person implements Cloneable{ // and does not immidiately suggest what .clone() exactly does
    public String [] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(names.clone(), (Address) address.clone());
    }
}
public class PrototypeViaClonable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "String"}, new Address("London Road", 123));
        Person jane = (Person) john.clone(); //!we copy the reference, they refer to the same object and so we change John's data
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;
        System.out.println(john);
        System.out.println(jane);

    }
}
