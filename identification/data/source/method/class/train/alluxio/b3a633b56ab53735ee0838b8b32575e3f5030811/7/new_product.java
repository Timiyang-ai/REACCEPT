public synchronized StorageTier getTier(int tierAlias) {
    return mAliasToTiers.get(tierAlias);
  }