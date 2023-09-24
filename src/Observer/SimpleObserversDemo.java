package Observer;

import java.util.ArrayList;
import java.util.List;

class PropertyChangedArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

interface Observer<T> {
    void handle(PropertyChangedArgs<T> args);
}

class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();
    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    protected void propertyChanged(T source, String propertyName, Object newValue) {
        for (Observer<T> o : observers) {
            o.handle(new PropertyChangedArgs<T>(source, propertyName, newValue));
        }
    }
}

class Person extends Observable<Person> {
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged(this, "age", age);
    }
}

public class SimpleObserversDemo implements Observer<Person> {
    public static void main(String[] args) {
        new SimpleObserversDemo();
    }

    public SimpleObserversDemo() {
        Person person = new Person();
        person.subscribe(this);
        for (int i = 20; i < 24; ++i)
            person.setAge(i);
    }

    @Override
    public void handle(PropertyChangedArgs<Person> args) {
        System.out.println("Person's " + args.propertyName + " has changed to " + args.newValue);
    }
}
