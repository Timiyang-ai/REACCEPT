private StorageDirView allocateBlock(long sessionId, long blockSize,
      BlockStoreLocation location) {
    Preconditions.checkNotNull(location, "location");
    StorageDirView candidateDirView = null;

    if (location.equals(BlockStoreLocation.anyTier())) {
      for (StorageTierView tierView : mManagerView.getTierViews()) {
        candidateDirView = getCandidateDirInTier(tierView, blockSize,
            BlockStoreLocation.ANY_MEDIUM);
        if (candidateDirView != null) {
          break;
        }
      }
    } else if (location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      StorageTierView tierView = mManagerView.getTierView(location.tierAlias());
      candidateDirView = getCandidateDirInTier(tierView, blockSize, BlockStoreLocation.ANY_MEDIUM);
    } else if (location.equals(BlockStoreLocation.anyDirInTierWithMedium(location.mediumType()))) {
      for (StorageTierView tierView : mManagerView.getTierViews()) {
        candidateDirView = getCandidateDirInTier(tierView, blockSize, location.mediumType());
        if (candidateDirView != null) {
          break;
        }
      }
    } else {
      StorageTierView tierView = mManagerView.getTierView(location.tierAlias());
      StorageDirView dirView = tierView.getDirView(location.dir());
      if (dirView.getAvailableBytes() >= blockSize) {
        candidateDirView = dirView;
      }
    }

    return candidateDirView;
  }