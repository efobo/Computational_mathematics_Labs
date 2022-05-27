package Math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {


    private final Expression expression;

    /**
     * Построить функцию
     */
    public Function(String function) {
        this.expression = new ExpressionBuilder(function).variables("x", "y").build();
    }


    /**
     * Применить значение функции в точке x
     */
    public double apply(double x) {
        return expression.setVariable("x", x).evaluate();
    }

    /**
     * Применить значение функции в точке x и y
     */
    public double apply(double x, double y) {
        return expression.setVariable("x", x).setVariable("y", y).evaluate();
    }



    /**
     * Вычислить производную функции в точке x
     */
    public double derivative(double x) {
        //double answer = (this.apply(x + delta) - this.apply(x - delta)) / (2 * delta);
        double answer = (this.apply(x + 1e-9) - this.apply(x - 1e-9)) / (2 * 1e-9);
        return answer;
    }

  
    public double secondDerivative(double x) {

        double answer = (this.apply(x+1e-9) + 2*this.apply(x) - this.apply(x-1e-9))/(1e-9*1e-9);
        return answer;
    }

    /**
     * Вычислить производную по x в точке (x, y)
     */
    public double derivativeByX(double x, double y, double delta) {
        return (this.apply(x + delta, y) - this.apply(x - delta, y)) / (2 * delta);
    }

    /**
     * Вычислить производную по y в точке (x, y)
     */
    public double derivativeByY(double x, double y, double delta) {
        return (this.apply(x, y + delta) - this.apply(x, y - delta)) / (2 * delta);
    }
}

