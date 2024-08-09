public static double requireNonNegative(double val) {
        if (Double.compare(val, 0d) < 0) {
            throw new IllegalArgumentException(format("%s is negative", val));
        }
        return val;
    }