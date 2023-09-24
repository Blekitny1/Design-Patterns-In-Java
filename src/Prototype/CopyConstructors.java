package Prototype;

class Address2 {
    public String streetAdress, city, country;

    public Address2(String streetAdress, String city, String country) {
        this.streetAdress = streetAdress;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAdress='" + streetAdress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Address2(Address2 other) {
        this(other.streetAdress, other.city, other.country);
    }
}

class Employee{
    public String name;
    public Address2 address;

    public Employee(String name, Address2 address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other){ // we created new object here, thus it is safe to change its field!
        name = other.name;
        address = new Address2(other.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}

public class CopyConstructors {
    public static void main(String[] args) {
        Employee john = new Employee("John", new Address2("123 London Road", "London", "UK"));
        Employee jane = new Employee(john); //we execute constructor from other Employee object
        jane.name = "Jane";
        System.out.println(john);
        System.out.println(jane);
    }
}
