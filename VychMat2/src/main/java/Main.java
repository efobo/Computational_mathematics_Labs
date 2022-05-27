

import java.util.Locale;
import java.util.Scanner;
import Math.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static NonlinearEquationsSolveMethods solver = new NonlinearEquationsSolveMethods();

    public static void main(String[] args) {
        System.out.println("Данная программа решает нелинейные уравнения\n");
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Решить нелинейное уравнение");
        System.out.println("(метод хорд и метод касательных)");
        System.out.println("2. Решить систему нелинейных уравнений");
        System.out.println("(метод простых итераций)\n");

        String input = "";
        while (!input.equals("1") && !input.equals("2")) {
            input = scanner.next();
            if (!input.equals("1") && !input.equals("2")) {
                System.out.println("Недопустимый ввод. Для продолжения введите \"1\" или \"2\"");
            }
        }
        if (input.equals("1")) chooseEquation();
        else if (input.equals("2")) chooseSystemOfEquations();
    }


    static void chooseEquation() {

        System.out.println("Выберите уравнение для решения");
        System.out.println("1. " + EquationLibrary.EQUATION[0]);
        System.out.println("2. " + EquationLibrary.EQUATION[1]);
        System.out.println("3. " + EquationLibrary.EQUATION[2]);
        System.out.println("4. " + EquationLibrary.EQUATION[3]);
        String input = "";
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
            input = scanner.next();
            if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                System.out.println("Недопустимый ввод. Для продолжения введите число от 1 до 4");
            }
        }

        if (input.equals("1")) solveEquation(0);
        else if (input.equals("2")) solveEquation(1);
        else if (input.equals("3")) solveEquation(2);
        else if (input.equals("4")) solveEquation(3);

    }

    static void solveEquation(int equationNumber) {
        Function functions = EquationLibrary.EQUATIONS[equationNumber];

        askAccuracy();
        double a;
        double b;
        while (true) {
            System.out.println("Задайте границы:");

            System.out.println("a (левая граница): ");
            a = scanner.nextDouble();
            System.out.println("b (правая граница): ");
            b = scanner.nextDouble();
            if (a < b) break;
            else System.out.println("a должно быть меньше b!");
        }

        double resultByChord = 0;
        double resultByTangents = 0;
        boolean hasSolutionByChord = false;
        boolean hasSolutionByTangents = false;

        System.out.println("Решение методом хорд: ");
        try {
            Object[] solution = solver.solveByChord(functions, a, b);
            System.out.println("x = " + solution[0] + ", количество итераций = " + solution[1]);
            resultByChord = Double.parseDouble(String.valueOf(solution[0]));
            hasSolutionByChord = true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage().toLowerCase(Locale.ROOT));
        }


        System.out.println("\nРешение методом касательных: ");
        try {
            Object[] solution = solver.solveByTangents(functions, a, b);
            System.out.println("x = " + solution[0] + ", количество итераций = " + solution[1]);
            resultByTangents = Double.parseDouble(String.valueOf(solution[0]));
            hasSolutionByTangents = true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage().toLowerCase(Locale.ROOT));
        }

        System.out.print("Разница между методами: ");
        if (hasSolutionByChord && hasSolutionByTangents) {
            System.out.println(Math.abs(resultByChord - resultByTangents));
        } else if (hasSolutionByChord) {
            System.out.println("\nНет решения по методу касательных");
        } else if (hasSolutionByTangents) {
            System.out.println("\nНет решения по методу хорд");
        } else System.out.println("\nНет решений по обоим методам");
    }

    static void askAccuracy() {

        System.out.println("Введите точность (число от 0 до 1): ");

        //double accuracy = Double.parseDouble(scanner.nextLine());
        String input = scanner.next();
        input = input.replace(",", ".");
        try {
            double accuracy = Double.parseDouble(input);
            if (0 >= accuracy || accuracy >= 1) {
                System.out.println("Недопустимый ввод.");
                askAccuracy();

            }
            solver.setAccuracy(accuracy);
        } catch (NumberFormatException e) {
            System.out.println("Недопустимый ввод.");
            askAccuracy();
        }


    }

    static void chooseSystemOfEquations() {
        System.out.println("Выберите систему уравнений для решения");
        for (int i = 0; i < EquationLibrary.SYSTEM.length; ++i) {
            System.out.println(i + 1 + ".");
            for (int j = 0; j < EquationLibrary.SYSTEM[i].length; ++j) {
                System.out.println(EquationLibrary.SYSTEM[i][j]);
            }
            System.out.println();
        }
        String input = "";
        while (!input.equals("1") && !input.equals("2")) {
            input = scanner.next();
            if (!input.equals("1") && !input.equals("2")) {
                System.out.println("Недопустимый ввод. Для продолжения введите число от 1 до 2");
            }
        }

        if (input.equals("1")) solveSystemOfEquations(0);
        else if (input.equals("2")) solveSystemOfEquations(1);

    }


    static void solveSystemOfEquations(int equationSysNumber) {
        Function[] systems = EquationLibrary.SYSTEMS[equationSysNumber];

        askAccuracy();

        double x;
        double y;

        System.out.println("Введите приближение x:");
        while (true) {
            try {
                x = scanner.nextDouble();
            } catch (NumberFormatException exp) {
                System.out.println("Введено число неверного формата");
                continue;
            }
            break;
        }

        System.out.println("Введите приближение y:");
        while (true) {
            try {
                y = scanner.nextDouble();
            } catch (NumberFormatException exp) {
                System.out.println("Введено число неверного формата");
                continue;
            }
            break;
        }

        System.out.println("Решение методом простых итераций: ");


        try {
            Object[] solution = solver.solveBySimpleIterationsMethod(x, y, systems);
            System.out.println("x = " + solution[0] + ", y = " + solution[1] + ", количество итераций = " + solution[2]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage().toLowerCase(Locale.ROOT));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage().toLowerCase(Locale.ROOT));
        }

    }
}




