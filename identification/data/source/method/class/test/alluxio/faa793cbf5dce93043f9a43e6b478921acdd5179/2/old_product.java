public void updateUsedBytes(Map<String, Long> usedBytesOnTiers) {
    mUsedBytes = 0;
    mUsedBytesOnTiers = usedBytesOnTiers;
    for (long t : mUsedBytesOnTiers.values()) {
      mUsedBytes += t;
    }
  }