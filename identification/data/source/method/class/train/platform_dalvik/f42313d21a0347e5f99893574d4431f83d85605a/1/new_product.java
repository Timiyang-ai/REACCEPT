public boolean get(int pos) {
        if (pos < 0) {
            // Negative index specified
            throw new IndexOutOfBoundsException(Msg.getString("K0006")); //$NON-NLS-1$
        }

        int arrayPos = pos >> OFFSET;
        if (arrayPos < actualArrayLength) {
            return (bits[arrayPos] & TWO_N_ARRAY[pos & RIGHT_BITS]) != 0;
        }
        return false;
    }