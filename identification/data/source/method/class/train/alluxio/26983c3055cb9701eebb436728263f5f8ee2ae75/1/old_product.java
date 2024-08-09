public synchronized List<StorageTier> getTiers() {
    return new ArrayList<StorageTier>(mTiers.values());
  }