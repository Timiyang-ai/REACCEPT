@Override
  public void removeAllFailure(Iterable<? extends K> entries, StoreAccessException e) {
    cleanup(entries, e);
  }