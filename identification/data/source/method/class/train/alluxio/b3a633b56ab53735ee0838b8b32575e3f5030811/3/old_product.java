public synchronized StorageTier getTier(int tierAlias) throws InvalidArgumentException {
    StorageTier tier = mAliasToTiers.get(tierAlias);
    if (tier == null) {
      throw new InvalidArgumentException("Cannot find tier with alias " + tierAlias);
    }
    return tier;
  }