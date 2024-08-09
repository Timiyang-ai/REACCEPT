public static int requirePositive(int val) {
        if (val < 1) {
            throw new IllegalArgumentException(val + " is not positive");
        }
        return val;
    }