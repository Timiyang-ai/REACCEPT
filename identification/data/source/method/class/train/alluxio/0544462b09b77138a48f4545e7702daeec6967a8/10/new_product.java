public synchronized StorageDir getDir(BlockStoreLocation location) {
    if (location.equals(BlockStoreLocation.anyTier())
        || location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      throw new IllegalArgumentException("Failed to get block path: " + location
          + " is not a specific dir.");
    }
    return getTier(location.tierAlias()).getDir(location.dir());
  }