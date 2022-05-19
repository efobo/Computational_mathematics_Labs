import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Как ты хочешь ввести данные?");
            System.out.println("1. Генерация случайной матрицы");
            System.out.println("2. Ввод из файла");

            input = scanner.next();

            if (!input.equals("1") && !input.equals("2")) {
                System.out.println("Неверный формат ввода");
                System.out.println("Введи \"1\" или \"2\"");
            }
        }

        int way = Integer.valueOf(input);
        input = "";

        System.out.println("\nМетод простых итераций");

        float e = 1;

        while (e >= 1) {
            try {
                System.out.println("Введите точность");
                input = scanner.next();
                input = input.replace(",", ".");
                e = Float.valueOf(input);
                if (e >= 1) System.out.println("Точность должна быть меньше единицы");
            } catch (Exception exp) {
                System.out.println("Введено некорректное значение");
            }
        }



        int[][] fullMatrix = new int[0][0];

        if (way == 1) {

            RandomMatrix randomMatrix = new RandomMatrix(scanner);
            fullMatrix = randomMatrix.makeRandomMatrix();


            //int [][] fullMatrix1 = {{20, 2, 3, 7, 5}, {1, 12, -2, -5, 4}, {5, -3, 13, 0, -3}, {0, 0, -1, 15, 7}};
            //Solution solution = new Solution(fullMatrix1, 0.1f);

        } else {
            File file;
            do {
                System.out.println("Введите имя файла");
                input = scanner.next();
                file = new File(input);
                if (!file.exists()) {
                    System.out.println("Файла с таким именем не существует");
                }
            } while (!file.exists());

            FileParser fileParser = new FileParser(file);

            try {
                fullMatrix = fileParser.takeMatrixFromFile();
                System.out.println("Матрица:");
                for (int i = 0; i < fullMatrix.length; ++i) {
                    for (int j = 0; j < fullMatrix.length+1; ++j) {
                        if (j == fullMatrix.length) System.out.print("| ");
                        System.out.print(fullMatrix[i][j] + " ");

                    }
                    System.out.println();
                }
            } catch (NullPointerException ex) {
                System.out.println("!!!Первая строчка в файле пустая. А там должно быть количество неизвестных!!!");
            }catch (IOException ex) {
                System.out.println("!!!Произошла ошибка парсинга!!!");
            }
        }

        //int [][] fullMatrix1 = {{20, 2, 3, 7, 5}, {1, 12, -2, -5, 4}, {5, -3, 13, 0, -3}, {0, 0, -1, 15, 7}};
        //Solution solution = new Solution(fullMatrix1, 0.1f);
        Solution solution = new Solution(fullMatrix, e);
        solution.solution();
    }
}
