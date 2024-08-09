public static void validatePositivePowerOfTwo(final int value)
    {
        if (!BitUtil.isPowerOfTwo(value))
        {
            throw new IllegalArgumentException("value must be a positive power of two: " + value);
        }
    }