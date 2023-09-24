package Adapter;

class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle {

    public int width; // a rectangle must have these fields
    public int height;

    public SquareToRectangleAdapter(Square square) {
        this.width = square.side; // which we set in constructor and that's it
        this.height = square.side;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}

public class AdapterExercise {
}
