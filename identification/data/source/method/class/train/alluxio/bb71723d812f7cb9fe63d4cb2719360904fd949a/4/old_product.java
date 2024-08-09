@Override
  public boolean equals(Object object) {
    if (object instanceof BlockStoreLocation
        && ((BlockStoreLocation) object).tierAlias() == tierAlias()
        && ((BlockStoreLocation) object).dir() == dir()) {
      return true;
    }
    return false;
  }