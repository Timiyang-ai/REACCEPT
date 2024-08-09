public int bitLength() {
        /*
         * Initialize bitLength field the first time this method is executed.
         * This method depends on the atomicity of int modifies; without
         * this guarantee, it would have to be synchronized.
         */
        if (bitLength == -1) {
            if (signum == 0) {
                bitLength = 0;
            } else {
                // Calculate the bit length of the magnitude
                int magBitLength = ((mag.length-1) << 5) + bitLen(mag[0]);

                if (signum < 0) {
                    // Check if magnitude is a power of two
                    boolean pow2 = (bitCnt(mag[0]) == 1);
                    for(int i=1; i<mag.length && pow2; i++)
                        pow2 = (mag[i]==0);

                    bitLength = (pow2 ? magBitLength-1 : magBitLength);
                } else {
                    bitLength = magBitLength;
                }
            }
        }
        return bitLength;
    }