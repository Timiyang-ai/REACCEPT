private StorageDirView allocateBlock(long userId, long blockSize, BlockStoreLocation location) {
    Preconditions.checkNotNull(location);

    if (location.equals(BlockStoreLocation.anyTier())) {
      int tierIndex = 0; // always starting from the first tier
      for (int i = 0; i < mManagerView.getTierViews().size(); i ++) {
        StorageTierView tierView = mManagerView.getTierViews().get(tierIndex);
        int dirViewIndex = getNextAvailDirInTier(tierView, blockSize);
        if (dirViewIndex >= 0) {
          mTierToLastDirMap.put(tierView, dirViewIndex); // update
          return tierView.getDirView(dirViewIndex);
        } else { // we didn't find one in this tier, go to next tier
          tierIndex ++;
        }
      }
    } else if (location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      StorageTierView tierView = mManagerView.getTierView(location.tierAlias());
      int dirViewIndex = getNextAvailDirInTier(tierView, blockSize);
      if (dirViewIndex >= 0) {
        mTierToLastDirMap.put(tierView, dirViewIndex); // update
        return tierView.getDirView(dirViewIndex);
      }
    } else {
      StorageTierView tierView = mManagerView.getTierView(location.tierAlias());
      StorageDirView dirView = tierView.getDirView(location.dir());
      if (dirView.getAvailableBytes() >= blockSize) {
        return dirView;
      }
    }

    return null;
  }