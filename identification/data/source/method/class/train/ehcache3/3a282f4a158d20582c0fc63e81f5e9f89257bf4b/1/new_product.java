@Override
  public void removeAllFailure(Iterable<? extends K> keys, StoreAccessException e) {
    cleanup(keys, e);
  }