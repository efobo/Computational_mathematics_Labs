package main.vychmat5.math;

import main.vychmat5.util.DiffFunction;

public class MilneMethod {
    private final int NUMBER_OF_INTERVALS = 100;
    private final int VAR_FOR_ROUND = 100000;
    private double first_x;
    private double first_y;
    private Double[][] coordinates;
    private DiffFunction diffFunction;
    private double last_x;
    private double h;

    public MilneMethod(double first_x, double first_y, DiffFunction diffFunction, double last_x) {
        this.first_x = first_x;
        this.first_y= first_y;
        this.diffFunction = diffFunction;
        this.last_x = last_x;
        this.h = (last_x - first_x)/NUMBER_OF_INTERVALS;
        this.coordinates = new Double[NUMBER_OF_INTERVALS][2];
    }

    public Double[][] solution() {
        coordinates = findFirstFourValues(first_x, first_y);
        for (int i = 4; i < NUMBER_OF_INTERVALS; ++i) {
            double x0 = coordinates[i-4][0];
            double x1 = coordinates[i-3][0];
            double x2 = coordinates[i-2][0];
            double x3 = coordinates[i-1][0];
            double x4 = x3 + h;

            double y0 = coordinates[i-4][1];
            double y1 = coordinates[i-3][1];
            double y2 = coordinates[i-2][1];
            double y3 = coordinates[i-1][1];
            // predictor formula
            double pY = y0 + 4*h/3*(2*diffFunction.apply(x1, y1) - diffFunction.apply(x2,y2) + 2*diffFunction.apply(x3,y3));

            double diffY4 = diffFunction.apply(x4, pY);
            // Corrector formula
            double cY = y2 + h/3*(diffFunction.apply(x2, y2) + 4*diffFunction.apply(x3, y3) + diffY4);
            double e = Math.abs(pY - cY)/29;
            double prevY = pY;
            double curY = cY;
            /*if (Double.isInfinite(cY) || Double.isNaN(cY)) {
                curY = pY;
            }*/
            diffY4 = diffFunction.apply(x4, curY);
            while(Math.abs(prevY - curY) > e) {
                prevY = curY;
                curY = y2 + h/3*(diffFunction.apply(x2, y2) + 4*diffFunction.apply(x3, y3) + diffY4);
                /*if (Double.isInfinite(curY) || Double.isNaN(curY)) {
                    curY = prevY;
                    break;
                }*/
                diffY4 = diffFunction.apply(x4, curY);
            }
            coordinates[i] = new Double[]{x4, cY};
            if (Double.isInfinite(coordinates[i][1]))
                System.out.println(i + " y = INFINITE");
            if (Double.isNaN(coordinates[i][1]))
                System.out.println(i + " y = NaN");
        }
        return coordinates;
    }


    private Double[][] findFirstFourValues(double first_x, double first_y) {
        // Метод Рунге-Кутты
        Double[][] coordinates = new Double[NUMBER_OF_INTERVALS][2];
        coordinates[0] = new Double[]{first_x, first_y};
        double k1;
        double k2;
        double k3;
        double k4;
        for (int i = 1; i < 4; ++i) {
            k1 = h*diffFunction.apply(coordinates[i-1][0], coordinates[i-1][1]);
            k2 = h*diffFunction.apply((coordinates[i-1][0] + h/2),(coordinates[i-1][1]+k1/2));
            k3 = h*diffFunction.apply((coordinates[i-1][0] + h/2), (coordinates[i-1][1]+k2/2));
            k4 = h*diffFunction.apply((coordinates[i-1][0] + h),(coordinates[i-1][1] + k3));
            double y = coordinates[i-1][1] + (k1+2*k2+2*k3+k4)/6;
            coordinates[i] = new Double[]{(coordinates[i-1][0]+h), y};
        }
        return coordinates;
    }
}
