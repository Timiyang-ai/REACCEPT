public synchronized void cleanupUser(long userId) {
    for (StorageTier tier : mTiers) {
      for (StorageDir dir : tier.getStorageDirs()) {
        dir.cleanupUser(userId);
      }
    }
  }