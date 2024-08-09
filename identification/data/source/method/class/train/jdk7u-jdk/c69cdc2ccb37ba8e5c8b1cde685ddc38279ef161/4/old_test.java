    public static void bitLength() {
        int failCount = 0;

        for (int i=0; i<size*10; i++) {
            int x = rnd.nextInt();
            BigInteger bigX = BigInteger.valueOf((long)x);
            int signBit = (x < 0 ? 0x80000000 : 0);
            int tmp = x, bitLength, j;
            for (j=0; j<32 && (tmp & 0x80000000)==signBit; j++)
                tmp <<= 1;
            bitLength = 32 - j;

            if (bigX.bitLength() != bitLength) {
                //System.err.println(x+": "+bitLength+", "+bigX.bitLength());
                failCount++;
            }
        }

        report("BitLength", failCount);
    }