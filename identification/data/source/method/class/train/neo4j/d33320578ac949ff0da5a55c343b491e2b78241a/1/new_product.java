static short checksumOf( long generation, long pointer )
    {
        short result = 0;
        result ^= ((short) generation) & UNSIGNED_SHORT_MASK;
        result ^= ((short) (generation >>> Short.SIZE)) & UNSIGNED_SHORT_MASK;
        result ^= ((short) pointer) & UNSIGNED_SHORT_MASK;
        result ^= ((short) (pointer >>> Short.SIZE)) & UNSIGNED_SHORT_MASK;
        result ^= ((short) (pointer >>> Integer.SIZE)) & UNSIGNED_SHORT_MASK;
        return result;
    }