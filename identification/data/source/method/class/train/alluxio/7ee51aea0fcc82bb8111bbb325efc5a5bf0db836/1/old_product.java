public synchronized Map<String, Long> getFreeBytesOnTiers() {
    Map<String, Long> freeCapacityBytes = new HashMap<String, Long>();
    for (Map.Entry<String, Long> entry : mTotalBytesOnTiers.entrySet()) {
      freeCapacityBytes.put(entry.getKey(),
          entry.getValue() - mUsedBytesOnTiers.get(entry.getKey()));
    }
    return freeCapacityBytes;
  }