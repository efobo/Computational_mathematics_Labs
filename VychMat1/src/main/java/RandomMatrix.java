
import java.util.Scanner;

public class RandomMatrix {
    Scanner scanner;

    public RandomMatrix(Scanner scanner) {
        this.scanner = scanner;
    }

    public int[][] makeRandomMatrix() {
        String input;
        int x = 21;
        while (x > 20)
        {
            try {
                System.out.println("Введите количество неизвестных ");
                input = scanner.next();
                x = Integer.valueOf(input);
                if (x > 21) System.out.println("Количество неизвестных не может превышать 20");
            } catch (Exception exp) {
                System.out.println("Введено некорректное значение");
            }
        }

        System.out.println("Матрица:");
        int fullMatrix[][] = new int [x][x+1];
        for (int i = 0; i < x; ++i)
        {
            for (int j = 0; j < x+1; ++j)
            {
                fullMatrix[i][j] = (int)(Math.random()*20);
                System.out.print(fullMatrix[i][j] + " ");
                if (j == x-1) System.out.print("| ");
            }
            System.out.println();
        }
        return fullMatrix;
    }


}
