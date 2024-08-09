public synchronized void updateUsedBytes(int aliasValue, long usedBytesOnTier) {
    mUsedBytes += usedBytesOnTier - mUsedBytesOnTiers.get(aliasValue - 1);
    mUsedBytesOnTiers.set(aliasValue - 1, usedBytesOnTier);
  }