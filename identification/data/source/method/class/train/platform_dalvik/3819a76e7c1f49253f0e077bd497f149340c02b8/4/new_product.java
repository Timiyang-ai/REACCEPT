@Override
    public int hashCode() {
        long x = 1234;
        for (int i = 0, length = actualArrayLength; i < length; i++) {
            x ^= bits[i] * (i + 1);
        }
        return (int) ((x >> 32) ^ x);
    }