private StorageDirView allocateBlock(long sessionId, long blockSize,
      BlockStoreLocation location) {
    Preconditions.checkNotNull(location);
    if (location.equals(BlockStoreLocation.anyTier())) {
      // When any tier is ok, loop over all tier views and dir views,
      // and return a temp block meta from the first available dirview.
      for (StorageTierView tierView : mManagerView.getTierViews()) {
        for (StorageDirView dirView : tierView.getDirViews()) {
          if (dirView.getAvailableBytes() >= blockSize) {
            return dirView;
          }
        }
      }
      return null;
    }

    int tierAlias = location.tierAlias();
    StorageTierView tierView = mManagerView.getTierView(tierAlias);
    if (location.equals(BlockStoreLocation.anyDirInTier(tierAlias))) {
      // Loop over all dir views in the given tier
      for (StorageDirView dirView : tierView.getDirViews()) {
        if (dirView.getAvailableBytes() >= blockSize) {
          return dirView;
        }
      }
      return null;
    }

    int dirIndex = location.dir();
    StorageDirView dirView = tierView.getDirView(dirIndex);
    if (dirView.getAvailableBytes() >= blockSize) {
      return dirView;
    }
    return null;
  }