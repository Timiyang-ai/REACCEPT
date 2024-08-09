@Override
  public boolean equals(Object object) {
    return object instanceof BlockStoreLocation
        && ((BlockStoreLocation) object).tierLevel() == tierLevel()
        && ((BlockStoreLocation) object).tierAlias() == tierAlias()
        && ((BlockStoreLocation) object).dir() == dir();
  }