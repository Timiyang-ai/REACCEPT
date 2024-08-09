public StorageTier getTier(int tierAlias) {
    StorageTier tier = mAliasToTiers.get(tierAlias);
    if (tier == null) {
      throw new IllegalArgumentException("Cannot find tier with alias " + tierAlias);
    }
    return tier;
  }