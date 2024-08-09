public synchronized Map<String, Long> getFreeBytesOnTiers() {
    Map<String, Long> freeCapacityBytes = new HashMap<String, Long>();
    for (String alias : mTotalBytesOnTiers.keySet()) {
      freeCapacityBytes.put(alias, mTotalBytesOnTiers.get(alias) - mUsedBytesOnTiers.get(alias));
    }
    return freeCapacityBytes;
  }