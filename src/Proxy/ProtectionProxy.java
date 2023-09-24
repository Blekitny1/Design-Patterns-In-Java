package Proxy;

interface Drivable {
    void drive();
}

class Car implements Drivable {

    protected Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Wrooooom");
    }
}

class Driver {

    public int age;

    public Driver(int age) {
        this.age = age;
    }

}

class CarProxy extends Car { // we want to add some checks
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age >= 18)
            super.drive();
        else
            System.out.println("Driver too young!");
    }
}

public class ProtectionProxy {
    public static void main(String[] args) {
        Car car = new Car(new Driver(12));
        car.drive();
        CarProxy carProxy = new CarProxy(new Driver(12)); // all code with Car class will work with CarProxy too
        carProxy.drive(); // we only have to change a type
    }
}
