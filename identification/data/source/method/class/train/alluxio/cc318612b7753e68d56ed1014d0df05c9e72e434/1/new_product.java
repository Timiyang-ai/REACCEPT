public boolean belongsTo(BlockStoreLocation location) {
    boolean tierInRange =
        tierAlias().equals(location.tierAlias()) || location.tierAlias().equals(ANY_TIER);
    boolean dirInRange = (dir() == location.dir()) || (location.dir() == ANY_DIR);
    return tierInRange && dirInRange;
  }