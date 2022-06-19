package main.vychmat5.util;

public class EquationsLibrary {
    public static final String[] STRING_EQUATIONS = new String[] {
            "y' = 1.6x + 0.5y^2",
            "y' = x^2 + y/2"
    };

    public static final DiffFunction[] EQUATIONS = new DiffFunction[] {
            new DiffFunction("1.6*x+0.5*y^2"),
            new DiffFunction("x^2+y/2")
    };
}
