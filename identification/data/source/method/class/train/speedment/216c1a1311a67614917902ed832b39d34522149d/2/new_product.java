public static int requirePositive(int val) {
        if (val < 1) {
            throw new IllegalArgumentException(val + IS_NOT_POSITIVE);
        }
        return val;
    }