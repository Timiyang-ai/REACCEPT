public static double requireNonPositive(double val) {
        if (Double.compare(val, 0d) > 0) {
            throw new IllegalArgumentException(val + IS_POSITIVE);
        }
        return val;
    }