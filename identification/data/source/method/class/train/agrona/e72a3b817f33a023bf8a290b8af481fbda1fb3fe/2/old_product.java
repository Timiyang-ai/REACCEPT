public static void validatePositivePowerOfTwo(final int value)
    {
        if (value > 0 && 1 == (value & (value - 1)))
        {
            throw new IllegalStateException("value must be a positive power of two");
        }
    }