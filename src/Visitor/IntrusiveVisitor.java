package Visitor;

abstract class Expression {
    // public abstract void print(StringBuilder sb); // breaks OCP and SRP
}

class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }


}

class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

}

class ExpressionPrinter {
    public static void print(Expression e, StringBuilder sb) {
        if (e.getClass() == DoubleExpression.class) { // this is slow
            sb.append(((DoubleExpression) e).value); // and a bit clunky
        } else if (e.getClass() == AdditionExpression.class) {
            AdditionExpression ae = (AdditionExpression) e;
            sb.append("(");
            print(ae.left, sb);
            sb.append("+");
            print(ae.right, sb);
            sb.append(")");
        }
    }
}

public class IntrusiveVisitor {
    public static void main(String[] args) {
        AdditionExpression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                )
        );
        // StringBuilder sb = new StringBuilder();
        // e.print(sb);
        // System.out.println(sb); //(1.0+(2.0+3.0))

        StringBuilder stringBuilder = new StringBuilder();
        ExpressionPrinter.print(e, stringBuilder);
        System.out.println(stringBuilder);
    }
}
