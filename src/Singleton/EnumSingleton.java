package Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumBasedSingleton { //we only cannot inherit from this
    INSTANCE; //that's it!

    EnumBasedSingleton() {
        value = 23;
    }

    private int value; // fields are not serialized, which can cause weird problems

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}


public class EnumSingleton {

    static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)) {
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            return (EnumBasedSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "myFile.bin";
        //EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
        //singleton.setValue(111); // new value
        //saveToFile(singleton, filename);

        EnumBasedSingleton singleton2 = readFromFile(filename); //if we read from file we get 23, because we only call constructor
        System.out.println(singleton2.getValue()); // 111
    }
}
