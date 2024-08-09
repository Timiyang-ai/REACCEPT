public synchronized StorageDir getDir(BlockStoreLocation location)
      throws InvalidArgumentException {
    if (location.equals(BlockStoreLocation.anyTier())
        || location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      throw new InvalidArgumentException("Failed to get block path: " + location
          + " is not a specific dir.");
    }
    return getTier(location.tierAlias()).getDir(location.dir());
  }