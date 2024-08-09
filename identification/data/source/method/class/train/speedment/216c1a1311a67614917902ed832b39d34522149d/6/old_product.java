public static double requireZero(double val) {
        if (Double.compare(val, 0d) == 0) {
            throw new IllegalArgumentException(format("%s is not zero", val));
        }
        return val;
    }