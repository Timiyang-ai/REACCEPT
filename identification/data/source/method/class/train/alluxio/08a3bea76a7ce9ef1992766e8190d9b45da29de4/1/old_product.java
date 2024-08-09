public boolean isRoot() {
    return mUri.getPath().equals(SEPARATOR)
        || (mUri.getPath().isEmpty() && mUri.getAuthority() != null);
  }