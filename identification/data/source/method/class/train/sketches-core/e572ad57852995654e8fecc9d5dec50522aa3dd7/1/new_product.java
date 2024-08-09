public static int getMaxSerializedSizeBytes(final int k, final long n) {
    final int numLevels = ubOnNumLevels(n);
    final int maxNumItems = computeTotalCapacity(k, DEFAULT_M, numLevels);
    return getSerializedSizeBytes(numLevels, maxNumItems);
  }