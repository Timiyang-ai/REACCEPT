public synchronized Set<StorageTier> getTiers() {
    return new HashSet<StorageTier>(mTiers.values());
  }