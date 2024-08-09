@ExperimentalApi
  public <T> void discardAll(Key<T> key) {
    List<MetadataEntry> removed = store.remove(key.name());
    storeCount -= removed != null ? removed.size() : 0;
  }