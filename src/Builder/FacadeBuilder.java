package Builder;

class Person2 {
    // address
    public String streetAddress, postcode, city;

    //employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// builder facade
class PersonBuilderFacade {
    protected Person2 person = new Person2();

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    public Person2 build() {
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilderFacade { //why do they have to inherit from builder?
    public PersonAddressBuilder(Person2 person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilderFacade {

    public PersonJobBuilder (Person2 person) {
        this.person = person;
    }
    public PersonJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position) {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
    }
}

public class FacadeBuilder {
    public static void main(String[] args) {
        PersonBuilderFacade pbf = new PersonBuilderFacade(); //common interface or class for subbuilders
        Person2 person = pbf
                .lives()
                    .at("123 London Road")
                    .in("London")
                    .withPostcode("SW123C")
                .works()
                    .at("Fabrikam")
                    .asA("engineer")
                    .earning(12345)
                .build();
        System.out.println(person); //looks very nice and is very simple to understand!
    }
}
