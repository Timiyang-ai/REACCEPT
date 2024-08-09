public boolean isRoot() {
    return mUri.getPath().equals(SEPARATOR)
        || (mUri.getPath().isEmpty() && hasAuthority());
  }