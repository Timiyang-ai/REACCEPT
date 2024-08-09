public List<StorageTier> getTiersBelow(int tierAlias) {
    int level = getTier(tierAlias).getTierLevel();
    return mTiers.subList(level + 1, mTiers.size());
  }