public static double root(double a, double b, Function f, double... args)
    {
        return root(1e-15, 1000, a, b, 0, f, args);
    }