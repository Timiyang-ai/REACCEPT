static Durability chooseDurabilityForGroupCommit(List<TabletMutations> mutations) {
    Durability result = Durability.NONE;
    for (TabletMutations tabletMutations : mutations) {
      if (tabletMutations.getDurability().ordinal() > result.ordinal()) {
        result = tabletMutations.getDurability();
      }
    }
    return result;
  }