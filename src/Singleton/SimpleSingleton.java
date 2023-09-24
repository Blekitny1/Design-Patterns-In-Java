package Singleton;

import java.io.*;

class BasicSingleton implements Serializable {
    private BasicSingleton() { //private constructor is the key!
    }

    private static final BasicSingleton INSTANCE = new BasicSingleton(); //single instance initialized inside class

    public static BasicSingleton getInstance() {
        return INSTANCE;
    } //the one and only instance that we expose

    private int value = 3;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // required for correct serialization
    // readResolve is used for _replacing_ the object read from the stream

    protected Object readResolve() //secures correct serialization - we have to return INSTANCE
    {
        return INSTANCE;
    }
}

public class SimpleSingleton {

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        //new BasicSingleton we can't call the constructor

        //BasicSingleton singleton = BasicSingleton.getInstance(); //but we can call the only instance
        //singleton.setValue(43);
        //System.out.println(singleton.getValue());

        //1. problem with reflection, we can call private constructor through it actually
        //2. serialization

        BasicSingleton singleton1 = BasicSingleton.getInstance();
        singleton1.setValue(111);

        String filename = "singleton.bin";
        saveToFile(singleton1, filename);

        singleton1.setValue(222);

        BasicSingleton singleton2 = readFromFile(filename);

        System.out.println(singleton1 == singleton2); //false
        System.out.println(singleton1.getValue()); //222
        System.out.println(singleton2.getValue()); //111

    }
}
