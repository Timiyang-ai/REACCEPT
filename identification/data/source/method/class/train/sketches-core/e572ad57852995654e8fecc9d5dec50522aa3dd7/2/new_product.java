public static int getMaxSerializedSizeBytes(final int k, final long n) {
    final int numLevels = KllHelper.ubOnNumLevels(n);
    final int maxNumItems = KllHelper.computeTotalCapacity(k, DEFAULT_M, numLevels);
    return getSerializedSizeBytes(numLevels, maxNumItems);
  }