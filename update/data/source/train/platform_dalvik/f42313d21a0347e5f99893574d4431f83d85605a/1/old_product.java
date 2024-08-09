public boolean get(int pos) {
        if (pos >= 0) {
            if (pos < bits.length * ELM_SIZE) {
                return (bits[pos / ELM_SIZE] & (1L << (pos % ELM_SIZE))) != 0;
            }
            return false;
        }
        // Negative index specified
        throw new IndexOutOfBoundsException(Msg.getString("K0006")); //$NON-NLS-1$
    }