@Override
  public TempBlockMeta allocateBlock(long userId, long blockId, long blockSize,
      BlockStoreLocation location) throws IOException {
    if (location.equals(BlockStoreLocation.anyTier())) {
      // When any tier is ok, loop over all storage tier and dir, and return the first dir that has
      // sufficient available space.
      for (StorageTier tier : mMetaManager.getTiers()) {
        for (StorageDir dir : tier.getStorageDirs()) {
          if (dir.getAvailableBytes() >= blockSize) {
            return new TempBlockMeta(userId, blockId, blockSize, dir);
          }
        }
      }
      return null;
    }

    int tierAlias = location.tierAlias();
    StorageTier tier = mMetaManager.getTier(tierAlias);
    if (location.equals(BlockStoreLocation.anyDirInTier(tierAlias))) {
      // Loop over all dirs in the given tier
      for (StorageDir dir : tier.getStorageDirs()) {
        if (dir.getAvailableBytes() >= blockSize) {
          return new TempBlockMeta(userId, blockId, blockSize, dir);
        }
      }
      return null;
    }

    int dirIndex = location.dir();
    StorageDir dir = tier.getDir(dirIndex);
    if (dir.getAvailableBytes() >= blockSize) {
      return new TempBlockMeta(userId, blockId, blockSize, dir);
    }
    return null;
  }