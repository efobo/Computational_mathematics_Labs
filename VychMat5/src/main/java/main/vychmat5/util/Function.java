package main.vychmat5.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class Function {
    private final Expression expression;
    private String expressionStr;

    public Function(String expr) {
        this.expressionStr = expr;
        this.expression = new ExpressionBuilder(expr).variable("x").build();
    }



    public Double apply(Double x) {
        try {
            return expression.setVariable("x", x).evaluate();
        } catch (RuntimeException e) {
            return Double.NaN;
        }
    }

    public String toString () {
        return expressionStr;
    }
}
