public static double requireNonPositive(double val) {
        if (Double.compare(val, 0d) > 0) {
            throw new IllegalArgumentException(format("%s is positive", val));
        }
        return val;
    }