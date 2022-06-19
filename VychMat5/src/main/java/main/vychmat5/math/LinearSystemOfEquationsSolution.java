package main.vychmat5.math;

public class LinearSystemOfEquationsSolution {
    double[][] matrixA;
    double[] matrixB;
    float e;

    public LinearSystemOfEquationsSolution(double[][] fullMatrix, float e) {
        this.matrixA = takeMatrixAFromFull(fullMatrix);
        this.matrixB = takeMatrixBFromFull(fullMatrix);
        this.e = e;
    }

    /*public double[] solution(){
        double[] answer = null;
        if (convergence()) {
            answer = new double[matrixA.length];
            int counter = 1;

            //System.out.println("\nИтерация " + counter + ", i = " + (counter - 1));
            double [] prevX = new double[matrixA.length];
            double [] curX = countNextX(prevX);

            counter++;
            while (!checkAccuracy(prevX, curX)) {
                prevX = curX;

                //System.out.println("\nИтерация " + counter + ", i = " + (counter - 1));
                curX = countNextX(prevX);


                counter++;
            }

            for (int i = 0; i < curX.length; ++i) {
                answer[i] = curX[i];
            }
        }
        return answer;
    }

    //reachAccuracy
    private boolean checkAccuracy(double[] prevX, double[] curX){
        System.out.println("Проверка точности");
        for (int i = 0; i < curX.length; ++i) {
            double delta = Math.abs(curX[i] - prevX[i]);
            System.out.print("delta" + (i+1) +" = |" + curX[i] + " - " + prevX[i] + "| = " + delta);
            if (delta > e) {
                System.out.println(" > " + e);
                System.out.println("Значит, считаем дальше!");
                return false;
            } else System.out.println(" < " + e);
        }

        return true;
    }

    private double [] countNextX(double [] prevX){
        double [] curX = new double[matrixA.length];
        for (int i = 0; i < curX.length; ++i) {
            double curXSum = matrixB[i];
            for (int j = 0; j < curX.length; ++j) {
                if (i != j)
                    curXSum -= matrixA[i][j]*prevX[j];
            }
            curX[i] = (curXSum / matrixA[i][i]);
        }
        return curX;
    }

    // isConvergence
    private boolean convergence() {
        System.out.println("Проверка на сходимость...");
        for (int i = 0; i < matrixA.length; ++i) {
            int sum = 0;
            for (int j = 1; j < matrixA.length; ++j) {
                if (i != j) {
                    sum += Math.abs(matrixA[i][j]);

                }

            }
            if (Math.abs(matrixA[i][i]) < sum) {
                System.out.println("Не сходится");
                return false;
            }
        }
        System.out.println("Сходится");
        return true;
    }*/

    private double[][] takeMatrixAFromFull(double[][] fullMatrix) {

        int rows = fullMatrix.length;
        int columns = fullMatrix[0].length;

        double[][] matrixA = new double [rows][columns - 1];
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < columns-1; ++j)
            {
                matrixA[i][j] = fullMatrix[i][j];
            }
        }
        return matrixA;
    }

    private double[] takeMatrixBFromFull(double[][] fullMatrix) {
        int rows = fullMatrix.length;
        int columns = fullMatrix[0].length;
        double[] matrixB = new double [rows];
        for (int i = 0; i < rows; ++i) {
            matrixB[i] = fullMatrix[i][columns - 1];
        }
        return matrixB;
    }


    public double[] solution() {
        double[] answer = null;
        int N  = matrixA.length;
        int n = matrixA.length;
        int m = matrixA.length;
        for (int p = 0; p < N; p++) {

            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(matrixA[i][p]) > Math.abs(matrixA[max][p])) {
                    max = i;
                }
            }
            double[] temp = matrixA[p]; matrixA[p] = matrixA[max]; matrixA[max] = temp;
            double   t    = matrixB[p]; matrixB[p] = matrixB[max]; matrixB[max] = t;

            if (Math.abs(matrixA[p][p]) <= 1e-10) {
                return null;
            }

            for (int i = p + 1; i < N; i++) {
                double alpha = matrixA[i][p] / matrixA[p][p];
                matrixB[i] -= alpha * matrixB[p];
                for (int j = p; j < N; j++) {
                    matrixA[i][j] -= alpha * matrixA[p][j];
                }
            }
        }

        // Обратный проход

        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += matrixA[i][j] * x[j];
            }
            x[i] = (matrixB[i] - sum) / matrixA[i][i];
        }

        /* Вывод результатов */

        if (n < m) return null;


        return x;
    }

}

