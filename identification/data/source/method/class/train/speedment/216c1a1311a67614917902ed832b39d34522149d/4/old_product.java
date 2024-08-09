public static double requirePositive(double val) {
        if (Double.compare(val, 0d) <= 0) {
            throw new IllegalArgumentException(format(
                "%s is not positive", val));
        }
        return val;
    }