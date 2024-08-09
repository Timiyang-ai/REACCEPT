public synchronized boolean isInTier(String targetTierAlias) {
    for (String tierAlias : mWorkerIdToAlias.values()) {
      if (tierAlias.equals(targetTierAlias)) {
        return true;
      }
    }
    return false;
  }