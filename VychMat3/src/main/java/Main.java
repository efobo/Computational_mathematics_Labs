import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        Integrator solver = new Integrator();
        System.out.println("Данная программа решает интегралы методом прямоугольников\n");
        Scanner scanner = new Scanner(System.in);
        String input;


        System.out.println("Выберите функцию для интегрирования: ");
        System.out.println("1. " + EquationsLibrary.STRING_EQUATIONS[0]);
        System.out.println("2. " + EquationsLibrary.STRING_EQUATIONS[1]);
        System.out.println("3. " + EquationsLibrary.STRING_EQUATIONS[2]);
        System.out.println("4. " + EquationsLibrary.STRING_EQUATIONS[3]);

        int functionNumber = 0;
        while(true) {
            input = scanner.nextLine();
            try{
                functionNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Вы должны ввести целое число от 1 до 4");
                continue;
            }
            if (functionNumber != 1 && functionNumber != 2 && functionNumber != 3 && functionNumber != 4) {
                System.out.println("Вы должны ввести целое число от 1 до 4");
                continue;
            }
            break;
        }
        if (functionNumber == 1) {
            solver.setFunction(EquationsLibrary.EQUATIONS[0]);
            solver.setOdz(EquationsLibrary.ODZ_OF_EQUATIONS[0]);
        }
        if (functionNumber == 2) {
            solver.setFunction(EquationsLibrary.EQUATIONS[1]);
            solver.setOdz(EquationsLibrary.ODZ_OF_EQUATIONS[1]);
        }
        if (functionNumber == 3) {
            solver.setFunction(EquationsLibrary.EQUATIONS[2]);
            solver.setOdz(EquationsLibrary.ODZ_OF_EQUATIONS[2]);
        }
        if (functionNumber == 4) {
            solver.setFunction(EquationsLibrary.EQUATIONS[3]);
            solver.setOdz(EquationsLibrary.ODZ_OF_EQUATIONS[3]);
        }

        double a = 0;
        double b = 0;


        while(true) {
            System.out.println("Введите границы интегрирования");
            System.out.print("a = ");
            input = scanner.nextLine();
            input.replaceAll(".", ",");
            try {
                a = Double.parseDouble(input);
            } catch (NumberFormatException e){
                System.out.println("Вы должны ввести число");
                continue;
            }
            System.out.print("b = ");
            input = scanner.nextLine();
            input.replaceAll(".", ",");
            try{
                b = Double.parseDouble(input);
            } catch (NumberFormatException e){
                System.out.println("Вы должны ввести число");
                continue;
            }
            if (a >= b) {
                System.out.println("Параметр a должен быть меньше параметра b!");
                continue;
            }
            break;
        }

        int n = 0;

        while (true) {
            System.out.println("Введите количество интервалов");
            input = scanner.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Вы должны ввести целое число");
                continue;
            }
            break;
        }
        solver.setNumberOfIntervals(n);

        Object[] solution;

        System.out.println("Метод левых прямоугольников: ");
        try {
            solution = solver.leftRectanglesMethod(a, b);
            double leftRectanglesSolution = Double.parseDouble(String.valueOf(solution[0]));
            System.out.println(leftRectanglesSolution);
            System.out.println(solution[1]);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Метод правых прямоугольников: ");
        try {
            solution = solver.rightRectanglesMethod(a, b);
            double rightRectanglesSolution = Double.parseDouble(String.valueOf(solution[0]));
            System.out.println(rightRectanglesSolution);
            System.out.println(solution[1]);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Метод средних прямоугольников: ");
        try {
            solution = solver.middleRectanglesMethod(a, b);
            double middleRectanglesSolution = Double.parseDouble(String.valueOf(solution[0]));
            System.out.println(middleRectanglesSolution);
            System.out.println(solution[1]);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
