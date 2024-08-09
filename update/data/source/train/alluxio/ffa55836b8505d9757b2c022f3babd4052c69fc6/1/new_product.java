public synchronized void cleanupUserTempBlocks(long userId, List<Long> tempBlockIds) {
    for (StorageTier tier : mTiers) {
      for (StorageDir dir : tier.getStorageDirs()) {
        dir.cleanupUserTempBlocks(userId, tempBlockIds);
      }
    }
  }