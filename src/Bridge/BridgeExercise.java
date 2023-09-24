package Bridge;

interface Renderer2
{
    public String whatToRenderAs();


}

abstract class Shape2
{
    public abstract String getName();
    protected Renderer2 renderer;

    public Shape2(Renderer2 renderer) {
        this.renderer = renderer;
    }
}

class Triangle extends Shape2
{
    public Triangle(Renderer2 renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }

    @Override
    public String toString() {
        String s1 = renderer.whatToRenderAs().substring(0, 8);
        String s2 = renderer.whatToRenderAs().substring(8);
        return s1 + this.getName() + s2;
    }
}

class Square extends Shape2
{
    public Square(Renderer2 renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Square";
    }

    @Override
    public String toString() {
        String s1 = renderer.whatToRenderAs().substring(0, 8);
        String s2 = renderer.whatToRenderAs().substring(8);
        return s1 + this.getName() + s2;
    }
}

class VectorRenderer2 implements Renderer2 {

    @Override
    public String whatToRenderAs() {
        return "Drawing  as lines";
    }
}

class RasterRenderer implements Renderer2 {

    @Override
    public String whatToRenderAs() {
        return "Drawing  as pixels";
    }
}

// imagine VectorTriangle and RasterTriangle are here too

public class BridgeExercise {
    public static void main(String[] args) {
        RasterRenderer pointRenderer = new RasterRenderer();
        VectorRenderer2 vectorRenderer = new VectorRenderer2();
        Square s = new Square(vectorRenderer);
        Triangle t = new Triangle(pointRenderer);
        System.out.println(s);
        System.out.println(t);
    }
}

//suggested solution

//abstract class Shape
//{
//  public abstract String getName();
//}
//
//class Triangle extends Shape
//{
//  @Override
//  public String getName()
//  {
//    return "Triangle";
//  }
//}
//
//class Square extends Shape
//{
//  @Override
//  public String getName()
//  {
//    return "Square";
//  }
//}
//
//class VectorSquare extends Square
//{
//  @Override
//  public String toString()
//  {
//    return String.format("Drawing %s as lines", getName());
//  }
//}
//
//class RasterSquare extends Square
//{
//  @Override
//  public String toString()
//  {
//    return String.format("Drawing %s as pixels", getName());
//  }
//}

// imagine VectorTriangle and RasterTriangle are here too

//interface Renderer
//{
//    public String whatToRenderAs();
//}
//
//abstract class Shape
//{
//    private Renderer renderer;
//    public String name;
//
//    public Shape(Renderer renderer)
//    {
//        this.renderer = renderer;
//    }
//
//    @Override
//    public String toString()
//    {
//        return String.format("Drawing %s as %s",
//                name, renderer.whatToRenderAs());
//    }
//}
//
//class Triangle extends Shape
//{
//    public Triangle(Renderer renderer)
//    {
//        super(renderer);
//        name = "Triangle";
//    }
//}
//
//class Square extends Shape
//{
//    public Square(Renderer renderer)
//    {
//        super(renderer);
//        name = "Square";
//    }
//}
//
//class RasterRenderer implements Renderer
//{
//
//    @Override
//    public String whatToRenderAs()
//    {
//        return "pixels";
//    }
//}
//
//class VectorRenderer implements Renderer
//{
//    @Override
//    public String whatToRenderAs()
//    {
//        return "lines";
//    }
//}