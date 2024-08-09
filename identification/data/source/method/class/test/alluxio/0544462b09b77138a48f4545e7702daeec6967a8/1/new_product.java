public List<StorageTier> getTiersBelow(String tierAlias) throws IllegalArgumentException {
    int ordinal = getTier(tierAlias).getTierOrdinal();
    return mTiers.subList(ordinal + 1, mTiers.size());
  }