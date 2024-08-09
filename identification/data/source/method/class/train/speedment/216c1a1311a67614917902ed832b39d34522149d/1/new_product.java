public static double requireNonZero(double val) {
        if (Double.compare(val, 0d) != 0) {
            throw new IllegalArgumentException(val + IS_ZERO);
        }
        return val;
    }