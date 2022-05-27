package Math;

public class NonlinearEquationsSolveMethods {
    private final int LIMIT = 1_000_000;

    private double ACCURACY = 1E-5;

    public void setAccuracy(double accuracy) {
        if (0 > accuracy && accuracy > 1) {
            throw new IllegalArgumentException("Точность должна принадлежать промежутку (0,1)");
        }
        this.ACCURACY = accuracy;
    }



    public Object[] solveByChord(Function function, double a, double b) throws RuntimeException{
        double functionAtA = function.apply(a);
        double functionAtB = function.apply(b);

        if (functionAtA*functionAtB >= 0) {
            throw new RuntimeException("Уравнение не сходится");
        }

        double secondDerivativeAtA = function.secondDerivative(a);
        double x0;
        double x;
        int iterations = 1;

        if (functionAtA*secondDerivativeAtA < 0) {
            x0 =a;
            x = x0 - function.apply(x0)/(function.apply(b) - function.apply(x0))*(b - x0);
            while (!isAccuracyMetForChordMethod(x, x0)) {
                x0 = x;
                x = x0 - function.apply(x0)/(function.apply(b) - function.apply(x0))*(b - x0);
                iterations++;
                if (iterations == LIMIT) {
                    throw new RuntimeException("Необходимая точность недостижима");
                }
            }
        } else {
            x0 = b;
            x = a - function.apply(a)/(function.apply(x0) - function.apply(a))*(x0 - a);
            while (!isAccuracyMetForChordMethod(x, x0)) {
                x0 = x;
                x = a - function.apply(a)/(function.apply(x0) - function.apply(a))*(x0 - a);
                iterations++;
                if (iterations == LIMIT) {
                    throw new RuntimeException("Необходимая точность недостижима");
                }
            }
        }

        return new Object[] {x, iterations};


    }

    private boolean isAccuracyMetForChordMethod(double curX, double prevX){
        if (Math.abs(curX - prevX) <= ACCURACY) return true;
        return false;
    }


    public Object[] solveByTangents(Function function, double a, double b) throws RuntimeException{

        if (function.apply(a)*function.apply(b) > 0) {
            throw new RuntimeException("Значения уравнения на границах должны быть разных знаков");
        }
        double prevX = a;
        if (checkConvergeForTangentsMethod(function, a, b) == 'b') {
            prevX = b;
        } else if (checkConvergeForTangentsMethod(function, a, b) == ' ') {
            throw new RuntimeException("Уравнение не сходится");
        }
        int iterations = 1;

        double functionAtPoint = function.apply(prevX);
        double derivativeAtPoint = function.derivative(prevX);
        double curX = prevX - (functionAtPoint/derivativeAtPoint);

        while (!isEndOfIterationForTangentsMethod(function, curX) && iterations < LIMIT) {
            iterations++;
            prevX = curX;
            functionAtPoint = function.apply(prevX);
            derivativeAtPoint = function.derivative(prevX);
            curX = prevX - (functionAtPoint/derivativeAtPoint);

        }
        if (iterations == LIMIT) {
            throw new RuntimeException("Необходимая точность недостижима");
        }
        return new Object[] { curX, iterations };
    }

    char checkConvergeForTangentsMethod(Function function, double a, double b) {
        double functionOnBorder = function.apply(a);
        double secondDerivativeOnBorder = function.secondDerivative(a);
        if ((functionOnBorder * secondDerivativeOnBorder) > 0) return 'a';
        functionOnBorder = function.apply(b);
        secondDerivativeOnBorder = function.secondDerivative(b);
        if ((functionOnBorder * secondDerivativeOnBorder) > 0) return 'b';
        return ' ';
    }

    boolean isEndOfIterationForTangentsMethod(Function function, double curX) {
        double functionAtPoint = function.apply(curX);
        if (Math.abs(functionAtPoint) < ACCURACY) return true;
        return false;
    }


    public Object[] solveBySimpleIterationsMethod(double x0, double y0, Function... functions) throws IllegalArgumentException, RuntimeException {
        if (functions.length != 2) {
            throw new IllegalArgumentException("Метод предназначен для решения систем, содержащих 2 или более уравнений");
        }

        double x = functions[0].apply(x0, y0);
        double y = functions[1].apply(x0, y0);
        int iterations = 1;

        while (!isAccuracyMetForSimpleIterationsMethod(x, x0, y, y0)) {
            x0 = x;
            y0 = y;
            x = functions[0].apply(x0, y0);
            y = functions[1].apply(x0, y0);
            iterations++;
            if (iterations == LIMIT) {
                throw new RuntimeException("Необходимая точность недостижима");
            }
        }

        return new Object[] { x, y, iterations };
    }

    public boolean isAccuracyMetForSimpleIterationsMethod(double curX, double prevX, double curY, double prevY){
        if (Math.abs(curX - prevX) <= ACCURACY && Math.abs(curY - prevY) <= ACCURACY) {
            return  true;
        }
        return false;
    }
}
