public static int ceilPowerOfTwo(int val) {
        final int LARGEST_FITTING_POWER_OF_TWO = Integer.MIN_VALUE >>> 1;

        if(val > LARGEST_FITTING_POWER_OF_TWO)
            throw new IllegalArgumentException("ceilPowerOfTwo overflows on values > " + LARGEST_FITTING_POWER_OF_TWO);

        if(val == Integer.MIN_VALUE)
            return 0;

        int result = val - 1;
        result = (result >> 1) | result;
        result = (result >> 2) | result;
        result = (result >> 4) | result;
        result = (result >> 8) | result;
        result = (result >> 16) | result;
        result++;
        return result;
    }