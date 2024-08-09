@Override
  public boolean equals(Object object) {
    return object instanceof BlockStoreLocation
        && ((BlockStoreLocation) object).tierAlias().equals(tierAlias())
        && ((BlockStoreLocation) object).dir() == dir();
  }