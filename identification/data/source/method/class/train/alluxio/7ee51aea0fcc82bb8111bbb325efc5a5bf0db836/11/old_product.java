public BlockStoreLocation toBlockStoreLocation() {
    return new BlockStoreLocation(mTier.getTierAlias(), mTier.getTierLevel(), mDirIndex);
  }