@Override
  public TempBlockMeta allocateBlock(long userId, long blockId, long blockSize,
      BlockStoreLocation location) throws IOException {

    if (location.equals(BlockStoreLocation.anyTier())) {
      int tierAlias = 0; //always starting from the first tier
      for (int i = 0; i < mMetaManager.getTiers().size(); i ++) {
        StorageTier tier = mMetaManager.getTiers().get(tierAlias);
        int dirIndex = getNextDirInTier(tier, blockSize);
        if (dirIndex >= 0) {
          mTierDirs.put(tier, dirIndex); // update
          return new TempBlockMeta(userId, blockId, blockSize, tier.getDir(dirIndex));
        } else { // we didn't find one in this tier, go to next tier
          tierAlias ++;
        }
      }
    } else if (location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      StorageTier tier = mMetaManager.getTier(location.tierAlias());
      int dirIndex = getNextDirInTier(tier, blockSize);
      if (dirIndex >= 0) {
        mTierDirs.put(tier, dirIndex); // update
        return new TempBlockMeta(userId, blockId, blockSize, tier.getDir(dirIndex));
      }
    } else {
      StorageTier tier = mMetaManager.getTier(location.tierAlias());
      StorageDir dir = tier.getDir(location.dir());
      if (dir.getAvailableBytes() >= blockSize) {
        return new TempBlockMeta(userId, blockId, blockSize, dir);
      }
    }

    return null;
  }