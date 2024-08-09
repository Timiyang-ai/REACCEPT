@Override
    public int hashCode() {
        long x = 1234;
        // for (int i = 0, length = bits.length; i < length; i+=2)
        // x ^= (bits[i] + ((long)bits[i+1] << 32)) * (i/2 + 1);
        for (int i = 0, length = bits.length; i < length; i++) {
            x ^= bits[i] * (i + 1);
        }
        return (int) ((x >> 32) ^ x);
    }