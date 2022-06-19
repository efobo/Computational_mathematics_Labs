package main.vychmat5.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Map;

public class DiffFunction {
    private final Expression expression;
    private String expressionStr;

    public DiffFunction(String expr) {
        this.expressionStr = expr;
        this.expression = new ExpressionBuilder(expr).variables("x", "y").build();
    }



    public Double apply(Double x, Double y) {
        try {
            Map<String, Double> varMap = new HashMap<>();
            varMap.put("x", x);
            varMap.put("y", y);
            return expression.setVariables(varMap).evaluate();

        } catch (RuntimeException e) {
            return Double.NaN;
        }
    }

    public String toString () {
        return expressionStr;
    }
}
