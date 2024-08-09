public static double requireNonZero(double val) {
        if (Double.compare(val, 0d) != 0) {
            throw new IllegalArgumentException(format("%s is zero", val));
        }
        return val;
    }