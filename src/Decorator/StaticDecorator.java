package Decorator;

import java.util.function.Supplier;

interface Shape2 {
    String info();
}

class Circle2 implements Shape2 {

    public float radius;

    public Circle2(float radius) {
        this.radius = radius;
    }

    public Circle2() {
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square2 implements Shape2 {

    private float side;

    public Square2() {}

    public Square2(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square with side " + side;
    }
}

class ColoredShape2<T extends Shape2> implements Shape2 {
    private Shape2 shape;
    private String color;

    public ColoredShape2(Supplier<? extends T> ctor, String color){
        shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape2<T extends Shape2> implements Shape2 {
    private Shape2 shape;
    private int transparency;

    public TransparentShape2(Supplier<? extends T> ctor, int transparency){
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}

public class StaticDecorator {
    public static void main(String[] args) {
        ColoredShape2<Square2> blueSquare = new ColoredShape2<>(() -> new Square2(20), "Blue");
        System.out.println(blueSquare.info());

        TransparentShape2<ColoredShape2<Circle2>> myCircle =
                new TransparentShape2<>(() -> new ColoredShape2<>(() -> new Circle2(5), "Green"), 50);
        System.out.println(myCircle.info());

        //myCircle.resize(2) we still cannot do it, since we do not inherit in Java
    }
}
