public synchronized void updateUsedBytes(List<Long> usedBytesByAlias) {
    mUsedBytes = 0;
    mUsedBytesByAlias = usedBytesByAlias;
    for (long t : mUsedBytesByAlias) {
      mUsedBytes += t;
    }
  }