import java.util.HashSet;
import java.util.Set;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public class Integrator {

    private int numberOfIntervals;
    private IFunction function;
    private double[] odz;

    public void integratorInitialization(int numberOfIntervals, double[] odz, IFunction function){
        setNumberOfIntervals(numberOfIntervals);
        setFunction(function);
        setOdz(odz);
    }

    private boolean isThereAvoidableGap (double x) {
        double limitBelow = Limit.limitFromBelow(function, x);
        double limitAbove = Limit.limitFromAbove(function, x);
        if (Double.isInfinite(limitBelow) || Double.isInfinite(limitAbove))
            return false;
        if (Double.isNaN(limitBelow) || Double.isNaN(limitAbove))
            return false;
         if (limitBelow != limitAbove)
             return false;

        return true;
    }

    private void checkInfinityOdz(double a, double b) throws RuntimeException {
        if (odz.length < 2) return;
        if (odz[0] == NEGATIVE_INFINITY) {
            if (a < odz[1]) throw new RuntimeException();
        }
        if (odz[odz.length-1] == POSITIVE_INFINITY) {
            if (b > odz[odz.length - 2]) throw new RuntimeException();
        }
    }


    private boolean isItInOdz(double x) {
        for (int i = 0; i < odz.length; ++i) {
            if (x == odz[i]) return true;
        }
        return false;
    }

    public Object[] leftRectanglesMethod(double a, double b) throws RuntimeException {
        try {
            checkInfinityOdz(a, b);
        } catch (RuntimeException e) {
            throw new RuntimeException("Функция не определена на интервале");
        }
        Set<Double> odzOnSegment = new HashSet();
        String message = "";
        for (int i = 0; i < odz.length; ++i) {
            if (a <= odz[i] && b>=odz[i] && !isThereAvoidableGap(odz[i]))
                throw new RuntimeException("Содержит неустранимый разрыв");
            else if (a <= odz[i] && b>=odz[i] && isThereAvoidableGap(odz[i])) {
                odzOnSegment.add(odz[i]);
            }
        }
        double step = (b-a)/ numberOfIntervals;
        double sum = Math.abs(function.apply(a));
        double curX = a;
        for (int i = 0; i < numberOfIntervals-1; ++i){
            curX += step;
            double funcAtX;
            if (odzOnSegment.contains(curX)) {
                message = "Устраняем точку разрыва, применяя среднее значение от двух соседних точек функции";
                funcAtX = averageOfTwoNeighboringPoints(curX);
            } else funcAtX = Math.abs(function.apply(curX));


            sum += funcAtX;

        }
        return new Object[]{sum*step, message};
    }

    public Object[] rightRectanglesMethod(double a, double b) throws RuntimeException {
        try {
            checkInfinityOdz(a, b);
        } catch (RuntimeException e) {
            throw new RuntimeException("Функция не определена на интервале");
        }
        Set<Double> odzOnSegment = new HashSet();
        String message = "";

        for (int i = 0; i < odz.length; ++i) {
            if (a <= odz[i] && b>=odz[i] && !isThereAvoidableGap(odz[i]))
                throw new RuntimeException("Содержит неустранимый разрыв");
        }
        double step = (b-a)/ numberOfIntervals;
        double sum = 0;
        double curX = a;
        for (int i = 0; i < numberOfIntervals; ++i){
            curX += step;
            double funcAtX;
            if (odzOnSegment.contains(curX)) {
                message = "Устраняем точку разрыва, применяя среднее значение от двух соседних точек функции";
                funcAtX = averageOfTwoNeighboringPoints(curX);
            } else funcAtX = Math.abs(function.apply(curX));
            sum += funcAtX;

        }
        return new Object[] {sum*step, message};
    }

    public Object[] middleRectanglesMethod(double a, double b) throws RuntimeException {
        try {
            checkInfinityOdz(a, b);
        } catch (RuntimeException e) {
            throw new RuntimeException("Функция не определена на интервале");
        }
        Set<Double> odzOnSegment = new HashSet();
        String message = "";
        for (int i = 0; i < odz.length; ++i) {
            if (a <= odz[i] && b>=odz[i] && !isThereAvoidableGap(odz[i]))
                throw new RuntimeException("Содержит неустранимый разрыв");
        }
        double step = (b-a)/ numberOfIntervals;
        double curX = a+step/2;
        double sum = Math.abs(function.apply(curX));
        for (int i = 0; i < numberOfIntervals-1; ++i) {
            curX += step;
            double funcAtX;
            if (odzOnSegment.contains(curX)) {
                message = "Устраняем точку разрыва, применяя среднее значение от двух соседних точек функции";
                funcAtX = averageOfTwoNeighboringPoints(curX);
            } else funcAtX = Math.abs(function.apply(curX));

            sum += funcAtX;
        }
        return new Object[] {sum*step, message};
    }

    private double averageOfTwoNeighboringPoints(double curX) {
        double e = 1e-5;
        double ret = function.apply(curX + e) * function.apply(curX - e)/2;
        return ret;
    }

    public void setNumberOfIntervals(int numberOfIntervals){
        this.numberOfIntervals = numberOfIntervals;
    }

    public void setOdz(double[] odz) {
        this.odz = odz;
    }

    public void setFunction(IFunction function){
        this.function = function;
    }
}
