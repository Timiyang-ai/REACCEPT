public static int getMaxSerializedSizeBytes(final int k, final long n) {
    final int numLevels = ub_on_num_levels(n);
    final int maxNumItems = computeTotalCapacity(k, DEFAULT_M, numLevels);
    return getSerializedSizeBytes(numLevels, maxNumItems);
  }