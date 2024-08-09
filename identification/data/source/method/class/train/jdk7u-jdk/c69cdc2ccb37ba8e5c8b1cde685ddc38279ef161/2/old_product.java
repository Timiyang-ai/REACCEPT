public int bitCount() {
        /*
         * Initialize bitCount field the first time this method is executed.
         * This method depends on the atomicity of int modifies; without
         * this guarantee, it would have to be synchronized.
         */
        if (bitCount == -1) {
            // Count the bits in the magnitude
            int magBitCount = 0;
            for (int i=0; i<mag.length; i++)
                magBitCount += bitCnt(mag[i]);

            if (signum < 0) {
                // Count the trailing zeros in the magnitude
                int magTrailingZeroCount = 0, j;
                for (j=mag.length-1; mag[j]==0; j--)
                    magTrailingZeroCount += 32;
                magTrailingZeroCount +=
                            trailingZeroCnt(mag[j]);

                bitCount = magBitCount + magTrailingZeroCount - 1;
            } else {
                bitCount = magBitCount;
            }
        }
        return bitCount;
    }