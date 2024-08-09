public synchronized StorageTier getTier(int tierAlias) throws IOException {
    StorageTier tier = mAliasToTiers.get(tierAlias);
    if (tier == null) {
      throw new IOException("Cannot find tier with alias " + tierAlias);
    }
    return tier;
  }