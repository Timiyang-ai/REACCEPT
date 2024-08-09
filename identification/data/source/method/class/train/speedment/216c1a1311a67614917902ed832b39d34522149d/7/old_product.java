public static double requireNegative(double val) {
        if (Double.compare(val, 0d) >= 0) {
            throw new IllegalArgumentException(format(
                "%s is not negative", val));
        }
        return val;
    }