public synchronized List<StorageTier> getTiersBelow(int tierAlias)
      throws InvalidArgumentException {
    int level = getTier(tierAlias).getTierLevel();
    return mTiers.subList(level + 1, mTiers.size());
  }