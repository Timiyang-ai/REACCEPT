public synchronized void updateUsedBytes(String tierAlias, long usedBytesOnTier) {
    mUsedBytes += usedBytesOnTier - mUsedBytesOnTiers.get(tierAlias);
    mUsedBytesOnTiers.put(tierAlias, usedBytesOnTier);
  }