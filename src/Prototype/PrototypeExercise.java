package Prototype;

class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        this(other.x, other.y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy() {
        return new Line(new Point(this.start), new Point(this.end));
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

public class PrototypeExercise {
    public static void main(String[] args) {
        Line l1 = new Line(new Point(1, 2), new Point(3, 4));
        Line l2 = l1.deepCopy();
        l2.end = new Point(5, 6);
        System.out.println(l1);
        System.out.println(l2);

    }
}
