public StorageTier getTier(String tierAlias) throws IllegalArgumentException {
    StorageTier tier = mAliasToTiers.get(tierAlias);
    if (tier == null) {
      throw new IllegalArgumentException(
          ExceptionMessage.TIER_ALIAS_NOT_FOUND.getMessage(tierAlias));
    }
    return tier;
  }