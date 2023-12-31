package Adapter;

import java.util.*;
import java.util.function.Consumer;

class Point2 {
    public int x, y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2 point2 = (Point2) o;
        return x == point2.x && y == point2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line2 {
    public Point2 start, end;

    public Line2(Point2 start, Point2 end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line2 line2 = (Line2) o;
        return Objects.equals(start, line2.start) && Objects.equals(end, line2.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}

class VectorObject2 extends ArrayList<Line2> {}
class VectorRectangle2 extends VectorObject2 {
    public VectorRectangle2(int x, int y, int width, int height) {
        add(new Line2(new Point2(x, y), new Point2(x + width, y)));
        add(new Line2(new Point2(x + width, y), new Point2(x + width, y + height)));
        add(new Line2(new Point2(x, y), new Point2(x, y + height)));
        add(new Line2(new Point2(x, y + height), new Point2(x + width, y + height)));
    }
}

class LineToPointAdapter2 implements Iterable<Point2> {
    private static int count = 0;
    private static Map<Integer, ArrayList<Point2>> cache = new HashMap<Integer, ArrayList<Point2>>();
    private int hash;

    public LineToPointAdapter2(Line2 line)
    {
        hash = line.hashCode();
        if (cache.get(hash) != null) return;

        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)",
                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

        ArrayList<Point2> point2s = new ArrayList<>();

        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0)
        {
            for (int y = top; y <= bottom; ++y)
            {
                point2s.add(new Point2(left, y));
            }
        }
        else if (dy == 0)
        {
            for (int x = left; x <= right; ++x)
            {
                point2s.add(new Point2(x, top));
            }
        }
        cache.put(hash, point2s);
    }

    @Override
    public Iterator<Point2> iterator() {
        return cache.get(hash).iterator();
    }

    @Override
    public void forEach(Consumer<? super Point2> action) {
        cache.get(hash).forEach(action);
    }

    @Override
    public Spliterator<Point2> spliterator() {
        return cache.get(hash).spliterator();
    }
}

public class AdapterCaching {

    private final static List<VectorObject2> vectorObjects2 = new ArrayList<>(Arrays.asList(
            new VectorRectangle2(1, 1, 10, 10),
            new VectorRectangle2(3, 3, 6, 6)
    ));

    public static void drawPoint(Point2 p) {
        System.out.println("."); // we want to adapt from this point API to use for drawing lines
    }

    private static void draw() {
        for (VectorObject2 vo : vectorObjects2) {
            for (Line2 line : vo) {
                LineToPointAdapter2 adapter = new LineToPointAdapter2(line);
                adapter.forEach(AdapterCaching::drawPoint);
            }
        }
    }

    public static void main(String[] args) {
        draw(); // we will see eight lines, which is correct for two rectangles, but we generate to many points :?
        draw(); // we don't replicate lines, we still have eight sets of points
        // watch out for adapters sometimes adapters generate too many data
    }
}
