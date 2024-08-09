public StorageDir getDir(BlockStoreLocation location) {
    if (location.equals(BlockStoreLocation.anyTier())
        || location.equals(BlockStoreLocation.anyDirInTier(location.tierAlias()))) {
      throw new IllegalArgumentException(
          ExceptionMessage.GET_DIR_FROM_NON_SPECIFIC_LOCATION.getMessage(location));
    }
    return getTier(location.tierAlias()).getDir(location.dir());
  }