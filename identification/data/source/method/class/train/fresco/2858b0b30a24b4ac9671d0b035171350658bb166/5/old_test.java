  private BinaryResource getResource(DiskStorage storage, final CacheKey key) throws IOException {
    return storage.getResource(CacheKeyUtil.getFirstResourceId(key), key);
  }