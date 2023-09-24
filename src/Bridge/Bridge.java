package Bridge;

// Shape -> Circle, Square ...
// Rendering -> Vector rendering, point rendering ...
// Vector Circle Renderer, Point Square Renderer ... we don't want it

interface Renderer {
    void renderCircle(float radius);
}

class VectorRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing circle with radius " + radius);
    }
}

class PointRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing points for circle with radius " + radius);
    }
}

abstract class Shape {

    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    public abstract void draw();
    public abstract void resize(float factor);
}

class Circle extends Shape {

    public float radius;
    public Circle(Renderer renderer) {
        super(renderer);
    }

    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw(){
        renderer.renderCircle(radius);
    }

    @Override
    public void resize(float factor) {
        radius *= factor;
    }

}

public class Bridge {
    public static void main(String[] args) {
        PointRenderer pointRenderer = new PointRenderer();
        VectorRenderer vectorRenderer = new VectorRenderer();
        Circle circle = new Circle(vectorRenderer, 5); // clunky design with supplying renderers by hand
        circle.draw(); // use frameworks for this instead;
        circle.resize(2);
        circle.draw();


    }
}
