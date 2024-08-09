public synchronized void updateUsedBytes(List<Long> usedBytesOnTiers) {
    mUsedBytes = 0;
    mUsedBytesOnTiers = usedBytesOnTiers;
    for (long t : mUsedBytesOnTiers) {
      mUsedBytes += t;
    }
  }