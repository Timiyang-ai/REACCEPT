@Override
  public TempBlockMeta allocateBlock(long userId, long blockId, long blockSize,
      BlockStoreLocation location) throws IOException {
    StorageDir candidateDir = null;

    if (location.equals(BlockStoreLocation.anyTier())) {
      for (StorageTier tier : mMetaManager.getTiers()) {
        candidateDir = getCandidateDirInTier(tier, blockSize);
        if (candidateDir != null) {
          return new TempBlockMeta(userId, blockId, blockSize, candidateDir);
        }
      }
    } else if (location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      StorageTier tier = mMetaManager.getTier(location.tierAlias());
      candidateDir = getCandidateDirInTier(tier, blockSize);
    } else {
      StorageTier tier = mMetaManager.getTier(location.tierAlias());
      StorageDir dir = tier.getDir(location.dir());
      if (dir.getAvailableBytes() >= blockSize) {
        candidateDir = dir;
      }
    }

    return candidateDir != null ? new TempBlockMeta(userId, blockId, blockSize, candidateDir)
        : null;
  }