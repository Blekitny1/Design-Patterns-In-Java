package Factory;

enum CoordinateSystem {
    CARTESIAN,
    POLAR
}

class Point {
    private double x, y;
/*
    private Point(double a, double b, CoordinateSystem cs) { //ugly constructor, a and b needs explanation
        switch (cs) {
            case CARTESIAN:
                this.x = a;
                this.y = b;
                break;
            case POLAR:
                this.x = a  * Math.cos(b);
                this.y = a * Math.sin(b);
                break;
        }

    }

    public Point(double rho, double theta) { //we cannot do this in java

    }
 */

    private Point(double x, double y) { // private constructor suggests usage of sth else => factory method
        this.x = x;
        this.y = y;
    }
    public static Point newCartesianPoint(double x, double y) { // name of method explain everything
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
    }

    public static class Factory { // we want to use constructor, but keep it private
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y); //we don't want to make constructor public, to keep one way of construction
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}



public class FirstFactoryMethods {

    public static void main(String[] args) {
        Point point = Point.Factory.newPolarPoint(2, 3); // understandable
        Point point2 = Point.Factory.newCartesianPoint(4, 5);
    }
}
