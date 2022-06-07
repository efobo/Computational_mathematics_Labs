import static java.lang.Double.NEGATIVE_INFINITY;

public class EquationsLibrary {

    public static final String[] STRING_EQUATIONS = new String[] {
            "1/ln(x)",
            "x^2 * sin(x)/10",
            "(x+1) * (x-2) / (x-2)",
            "1 / x"
    };

    public static final double[][] ODZ_OF_EQUATIONS = {
            {NEGATIVE_INFINITY, 0},
            {},
            {2},
            {0}
    };

    public static final IFunction[] EQUATIONS = new IFunction[]{
            f -> {return 1/Math.log(f);},
            f -> {return f*f*Math.sin(f)/10;},
            f -> {return (f+1);},
            f -> {return 1/f;}
    };
}
