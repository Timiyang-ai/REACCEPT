    public static void bitCount() {
        int failCount = 0;

        for (int i=0; i<size*10; i++) {
            int x = rnd.nextInt();
            BigInteger bigX = BigInteger.valueOf((long)x);
            int bit = (x < 0 ? 0 : 1);
            int tmp = x, bitCount = 0;
            for (int j=0; j<32; j++) {
                bitCount += ((tmp & 1) == bit ? 1 : 0);
                tmp >>= 1;
            }

            if (bigX.bitCount() != bitCount) {
                //System.err.println(x+": "+bitCount+", "+bigX.bitCount());
                failCount++;
            }
        }
        report("Bit Count", failCount);
    }