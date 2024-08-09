public void updateUsedBytes(Map<String, Long> usedBytesOnTiers) {
    mUsedBytes = 0;
    mUsedBytesOnTiers = new HashMap<>(usedBytesOnTiers);
    for (long t : mUsedBytesOnTiers.values()) {
      mUsedBytes += t;
    }
  }