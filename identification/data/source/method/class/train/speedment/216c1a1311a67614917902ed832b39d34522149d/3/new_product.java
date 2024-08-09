public static double requireNonNegative(double val) {
        if (Double.compare(val, 0d) < 0) {
            throw new IllegalArgumentException(val + IS_NEGATIVE);
        }
        return val;
    }