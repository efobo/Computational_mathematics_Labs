package main.vychmat41;

public class CubicSplinesInterpolation {
    private LinearSystemOfEquationsSolution linearSystemOfEquationsSolution;
    private Double[] valuesX;
    private Double[] valuesY;

    public CubicSplinesInterpolation(Double[] valuesX, Double[] valuesY) {
        this.valuesX = valuesX;
        this.valuesY = valuesY;
    }

    public Function[] solution () throws RuntimeException{
        Function[] solutionFunction = new Function[valuesX.length-1];
        linearSystemOfEquationsSolution = new LinearSystemOfEquationsSolution(makeFullMatrix(), 0.1f);
        double[] systemSolution = linearSystemOfEquationsSolution.solution();

        if (systemSolution != null) {
            String funcStr = "";
            int systemIndex = 0;
            for (int i = 0; i < valuesX.length - 1; ++i) {
                funcStr += systemSolution[systemIndex] + "+" + systemSolution[systemIndex + 1] + "*(x-" + valuesX[i] + ")+" +
                        systemSolution[systemIndex + 2] + "*(x-" + valuesX[i] + ")^2+" +
                        systemSolution[systemIndex + 3] + "*(x-" + valuesX[i] + ")^3";

                solutionFunction[i] = new Function(funcStr);
                funcStr = "";
                systemIndex += 4;
            }
        } else throw new RuntimeException("Полученная система уравнений не удовлетворяет условиям сходимости");
        return solutionFunction;
    }
    private double [][] makeFullMatrix() {

        //количество полей А (количество переменных)
        int rowsA = (valuesX.length-1)*4;

        double[][] fullMatrix = makeZeroMatrix(rowsA, rowsA+1);

        //a1 + b1*0 + c1*0 + d1*0 = y1
        for (int i = 0; i < valuesX.length-1; ++i) {
            fullMatrix[i][i*4] =1;

            fullMatrix[i][rowsA] = valuesY[i];
        }

        //a1 + b1*(x2-x1) + c1*(x2-x1)^2 + d1*(x2-x1)^3 = y2
        int indexForColumns = 0;
        for (int i = valuesX.length-1; i < 2*(valuesX.length-1); ++i) {
            double delta = valuesX[(i+1)-(valuesX.length-1)] - valuesX[i - (valuesX.length-1)];
            //a1
            fullMatrix[i][indexForColumns] = 1;
            //b1*(x2-x1)
            fullMatrix[i][indexForColumns+1] = delta;
            //c1*(x2-x1)^2
            fullMatrix[i][indexForColumns+2] = delta * delta;
            //d1*(x2-x1)^3
            fullMatrix[i][indexForColumns+3] = delta * delta * delta;
            // = y2
            fullMatrix[i][rowsA] = valuesY[(i+1) - (valuesX.length-1)];
            indexForColumns += 4;
        }
        indexForColumns = 1;

        // b1 + 2*c1*(x2-x1) + 3*d1*(x2-x1)^2 - b2 = 0
        for (int i = 2*(valuesX.length-1); i < 3*(valuesX.length-1)-1; ++i) {

            // b1
            fullMatrix[i][indexForColumns] = 1;
            // 2*c1*(x2-x1)
            fullMatrix[i][indexForColumns+1] = 2*(valuesX[(i+1) - 2*(valuesX.length-1)] - valuesX[i - 2*(valuesX.length-1)]);
            // 3*d1*(x2-x1)^2
            fullMatrix[i][indexForColumns+2] = 3*(valuesX[(i+1) - 2*(valuesX.length-1)] -
                    valuesX[i - 2*(valuesX.length-1)])*(valuesX[(i+1) -
                    2*(valuesX.length-1)] - valuesX[i - 2*(valuesX.length-1)]);
            // -b2
            fullMatrix[i][indexForColumns+4] = -1;
            indexForColumns+=4;
        }

        indexForColumns = 2;

        // 2*c1 + 6*d1*(x2-x1) - 2*c2 = 0
        for (int i = 3*(valuesX.length-1)-1; i < rowsA-2; ++i)  {
            // 2*c1
            fullMatrix[i][indexForColumns] = 2;
            // 6*d1*(x2-x1)
            fullMatrix[i][indexForColumns+1] = 6*(valuesX[(i+1) - (3*(valuesX.length-1)-1)] - valuesX[i - (3*(valuesX.length-1)-1)]);
            // -2*c2
            fullMatrix[i][indexForColumns+4] = -2;
            indexForColumns+=4;
        }

        // левая граница
        //2c1 = 0
        fullMatrix[rowsA-2][2] = 2;
        //правая граница
        //2*cn + 6*dn*(xn+1 - xn)
        //2*cn
        fullMatrix[rowsA-1][rowsA-4] = 2;
        // 6*dn*(xn+1 - xn)
        fullMatrix[rowsA-1][rowsA-3] = 6 * (valuesX[valuesX.length-1] - valuesX[valuesX.length-2]);

        return fullMatrix;
    }

    private double[][] makeZeroMatrix(int rows, int columns){
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] =0;
            }
        }
        return matrix;
    }
}
