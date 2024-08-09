public synchronized void updateUsedBytes(int aliasValue, long usedBytesOfAlias) {
    mUsedBytes += usedBytesOfAlias - mUsedBytesByAlias.get(aliasValue - 1);
    mUsedBytesByAlias.set(aliasValue - 1, usedBytesOfAlias);
  }