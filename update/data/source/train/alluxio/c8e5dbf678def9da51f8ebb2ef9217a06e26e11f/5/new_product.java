public BlockStoreLocation getBlockLocation() {
    StorageTier tier = mDir.getParentTier();
    return new BlockStoreLocation(tier.getTierAlias(), mDir.getDirIndex(), mDir.getDirMedium());
  }