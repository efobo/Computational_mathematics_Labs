public class Solution {
    int[][] matrixA;
    int[] matrixB;
    float e;

    public Solution(int[][] fullMatrix, float e) {
        this.matrixA = takeMatrixAFromFull(fullMatrix);
        this.matrixB = takeMatrixBFromFull(fullMatrix);
        this.e = e;
    }

    public void solution(){
        if (convergence()) {
            int counter = 1;

            System.out.println("\nИтерация " + counter + ", i = " + (counter - 1));
            float [] prevX = new float[matrixA.length];
            float [] curX = countNextX(prevX);
            for (int i = 0; i < curX.length; ++i)
            {
                System.out.println("x" + (i+1) + " = " +curX[i]);
            }
            counter++;
            while (!checkAccuracy(prevX, curX)) {
                prevX = curX;

                System.out.println("\nИтерация " + counter + ", i = " + (counter - 1));
                curX = countNextX(prevX);
                for (int i = 0; i < curX.length; ++i)
                {
                    System.out.println("x" + (i+1) + " = " +curX[i]);
                }

                counter++;
            }

            System.out.println("\nОтвет:");
            for (int i = 0; i < curX.length; ++i) {
                System.out.println("x" + (i+1) + " = " + curX[i]);
            }
            System.out.println("С точностью " + e);
            System.out.println("Произведено " + (counter - 1) + " итераций");
        } else System.out.println("\nНет решений");

    }

    //reachAccuracy
    private boolean checkAccuracy(float[] prevX, float[] curX){
        System.out.println("Проверка точности");
        for (int i = 0; i < curX.length; ++i) {
            float delta = Math.abs(curX[i] - prevX[i]);
            System.out.print("delta" + (i+1) +" = |" + curX[i] + " - " + prevX[i] + "| = " + delta);
            if (delta > e) {
                System.out.println(" > " + e);
                System.out.println("Значит, считаем дальше!");
                return false;
            } else System.out.println(" < " + e);
        }

        return true;
    }

    private float [] countNextX(float [] prevX){
        float [] curX = new float[matrixA.length];
        for (int i = 0; i < curX.length; ++i) {
            float curXSum = matrixB[i];
            for (int j = 0; j < curX.length; ++j) {
                if (i != j)
                    curXSum -= matrixA[i][j]*prevX[j];
            }
            curX[i] = curXSum / matrixA[i][i];
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
    }

    private int[][] takeMatrixAFromFull(int[][] fullMatrix) {

        int rows = fullMatrix.length;
        int columns = fullMatrix[0].length;

        int[][] matrixA = new int [rows][columns - 1];
        System.out.println("\nМатрица А");
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < columns-1; ++j)
            {
                matrixA[i][j] = fullMatrix[i][j];
                System.out.print(matrixA[i][j] + " ");
            }
            System.out.println();
        }
        return matrixA;
    }

    private int[] takeMatrixBFromFull(int[][] fullMatrix) {
        int rows = fullMatrix.length;
        int columns = fullMatrix[0].length;
        System.out.println("\nМатрица B");
        int[] matrixB = new int [rows];
        for (int i = 0; i < rows; ++i) {
            matrixB[i] = fullMatrix[i][columns - 1];
            System.out.println(matrixB[i]);
        }
        return matrixB;
    }
}
