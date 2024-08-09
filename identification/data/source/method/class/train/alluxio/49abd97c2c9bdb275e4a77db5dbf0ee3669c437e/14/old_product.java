private TempBlockMeta allocateBlock(long userId, long blockId, long blockSize,
      BlockStoreLocation location) throws IOException {
    StorageDirView candidateDirView = null;

    if (location.equals(BlockStoreLocation.anyTier())) {
      for (StorageTierView tierView : mManagerView.getTierViews()) {
        candidateDirView = getCandidateDirInTier(tierView, blockSize);
        if (candidateDirView != null) {
          return candidateDirView.createTempBlockMeta(userId, blockId, blockSize);
        }
      }
    } else if (location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      StorageTierView tierView = mManagerView.getTierView(location.tierAlias());
      candidateDirView = getCandidateDirInTier(tierView, blockSize);
    } else {
      StorageTierView tierView = mManagerView.getTierView(location.tierAlias());
      StorageDirView dirView = tierView.getDirView(location.dir());
      if (dirView.getAvailableBytes() >= blockSize) {
        candidateDirView = dirView;
      }
    }

    return candidateDirView != null
        ? candidateDirView.createTempBlockMeta(userId, blockId, blockSize) : null;
  }