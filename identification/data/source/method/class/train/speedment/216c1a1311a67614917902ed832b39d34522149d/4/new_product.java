public static double requirePositive(double val) {
        if (Double.compare(val, 0d) <= 0) {
            throw new IllegalArgumentException(val + IS_NOT_POSITIVE);
        }
        return val;
    }