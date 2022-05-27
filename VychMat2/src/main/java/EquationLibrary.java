import Math.Function;

public class EquationLibrary {
    public static final Function[] EQUATIONS = new Function[]
            {new Function("cos(2/x)-2sin(1/x)+1/x") // 1 [1.8; 2]
                    ,
                    new Function("x^2-e^x-3x+2") // 2 [0; 1] 0,4
                    ,
                    new Function("e^(-x)-0.5(sin(x))^2") // 3 [1; 1.5]
                    ,
                    new Function("x^3-17") // 4 [1; 3] 2,57

            };



    public static final String[] EQUATION = new String[] {
            new String("cos(2/x) - 2sin(1/x) + 1/x = 0") // Tangents
            ,
            new String("x^2 - e^x - 3x + 2 = 0")
            ,
            new String("e^(-x) - 0.5(sin(x))^2 = 0") // Tangents
            ,
            new String("x^3 - 17 = 0")
    };

    public static final Function[][] SYSTEMS = new Function[][] {
            {
                    new Function("cos(y)/3+0.3"), new Function("sin(x-0.6)-1.6") // 1 // x0 = 0,15 y0 = -2
            },
            {
                    new Function("((x(y+5)-1)/2)^0.5"), new Function("(x + 3log10(x))^0.5")// 2 // x0 = 3,5 y0 = 2,2
            }
    };



    public static final String[][] SYSTEM = new String[][] {
            {
                    new String("x = cos(y)/3 + 0.3"), new String("y = sin(x - 0.6) - 1.6")
            },
            {
                    new String("2x^2 - xy - 5x + 1 = 0"), new String("x + 3log(x) - y^2 = 0")
            }
    };
}
