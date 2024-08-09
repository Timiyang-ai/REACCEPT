  static Durability chooseDurabilityForGroupCommit(Collection<TabletMutations> mutations) {
    Durability result = Durability.NONE;
    for (TabletMutations tabletMutations : mutations) {
      result = DfsLogger.maxDurability(tabletMutations.getDurability(), result);
    }
    return result;
  }