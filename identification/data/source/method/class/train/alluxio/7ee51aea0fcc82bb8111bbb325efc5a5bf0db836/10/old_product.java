public synchronized List<Long> getFreeBytesOnTiers() {
    List<Long> freeCapacityBytes = new ArrayList<Long>();
    for (int i = 0; i < mTotalBytesOnTiers.size(); i ++) {
      freeCapacityBytes.add(mTotalBytesOnTiers.get(i) - mUsedBytesOnTiers.get(i));
    }
    return freeCapacityBytes;
  }