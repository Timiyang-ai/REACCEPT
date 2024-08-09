public void ensureCapacity(@Nonnegative long maximumSize) {
    Caffeine.requireArgument(maximumSize >= 0);
    int maximum = (int) Math.min(maximumSize, Integer.MAX_VALUE >>> 1);
    if ((table != null) && (table.length >= maximum)) {
      return;
    }

    table = (maximum == 0) ? EMPTY_TABLE : new long[ceilingNextPowerOfTwo(maximum)];
    tableMask = Math.max(0, table.length - 1);
    sampleSize = (10 * maximum);
    if (sampleSize <= 0) {
      sampleSize = Integer.MAX_VALUE;
    }
    size = 0;
  }