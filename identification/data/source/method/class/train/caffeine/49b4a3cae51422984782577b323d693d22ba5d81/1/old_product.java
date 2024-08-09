public void ensureCapacity(@Nonnegative long maximumSize) {
    Caffeine.requireArgument(maximumSize >= 0);
    int maximum = (int) Math.min(maximumSize, Integer.MAX_VALUE);
    if ((table != null) && (table.length >= maximum)) {
      return;
    }

    table = new long[ceilingNextPowerOfTwo(maximum)];
    tableMask = table.length - 1;
    sampleSize = (10 * maximum);
    if (sampleSize <= 0) {
      sampleSize = Integer.MAX_VALUE;
    }
  }