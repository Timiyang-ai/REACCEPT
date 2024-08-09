public static double requireNegative(double val) {
        if (Double.compare(val, 0d) >= 0) {
            throw new IllegalArgumentException(val + IS_NOT_NEGATIVE);
        }
        return val;
    }