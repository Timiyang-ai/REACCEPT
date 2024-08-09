public static int ceilPowerOfTwo(int val) {
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