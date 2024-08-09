public int bitLength() {
        @SuppressWarnings("deprecation") int n = bitLength - 1;
        if (n == -1) { // bitLength not initialized yet
            int[] m = mag;
            int len = m.length;
            if (len == 0) {
                n = 0; // offset by one to initialize
            }  else {
                // Calculate the bit length of the magnitude
                int magBitLength = ((len - 1) << 5) + bitLengthForInt(mag[0]);
                 if (signum < 0) {
                     // Check if magnitude is a power of two
                     boolean pow2 = (Integer.bitCount(mag[0]) == 1);
                     for(int i=1; i< len && pow2; i++)
                         pow2 = (mag[i] == 0);

                     n = (pow2 ? magBitLength -1 : magBitLength);
                 } else {
                     n = magBitLength;
                 }
            }
            bitLength = n + 1;
        }
        return n;
    }