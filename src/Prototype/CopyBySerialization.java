package Prototype;

import java.io.Serializable;

class Foo implements Serializable{
    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}

public class CopyBySerialization {

    public static void main(String[] args) {
        Foo foo1 = new Foo(42, "ew");
        // Foo foo2 = SerializationUtils.roundtrip(foo1); use a good library for serialization!

        // foo2.whatever = "we";
        System.out.println(foo1);
        // System.out.println(foo2);
    }
}
