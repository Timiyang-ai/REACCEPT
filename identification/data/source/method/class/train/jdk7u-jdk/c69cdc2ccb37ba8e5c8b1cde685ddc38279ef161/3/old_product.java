private static int bitLength(int[] val, int len) {
        if (len==0)
            return 0;
        return ((len-1)<<5) + bitLen(val[0]);
    }