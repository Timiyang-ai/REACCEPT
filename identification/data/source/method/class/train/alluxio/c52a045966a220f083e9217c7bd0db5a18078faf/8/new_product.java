public synchronized long getAvailableBytes(BlockStoreLocation location) {
    long spaceAvailable = 0;

    if (location.equals(BlockStoreLocation.anyTier())) {
      for (StorageTier tier : mTiers) {
        spaceAvailable += tier.getAvailableBytes();
      }
      return spaceAvailable;
    }

    int tierAlias = location.tierAlias();
    StorageTier tier = getTier(tierAlias);
    // TODO: This should probably be max of the capacity bytes in the dirs?
    if (location.equals(BlockStoreLocation.anyDirInTier(tierAlias))) {
      return tier.getAvailableBytes();
    }

    int dirIndex = location.dir();
    StorageDir dir = tier.getDir(dirIndex);
    return dir.getAvailableBytes();
  }