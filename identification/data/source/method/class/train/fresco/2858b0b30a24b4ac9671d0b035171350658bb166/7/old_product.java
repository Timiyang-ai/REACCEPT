@Override
  public BinaryResource getResource(final CacheKey key) {
    try {
      synchronized (mLock) {
        FileBinaryResource resource = mStorageSupplier.get().getResource(getResourceId(key), key);
        if (resource == null) {
          mCacheEventListener.onMiss();
        } else {
          mCacheEventListener.onHit();
        }
        return resource;
      }
    } catch (IOException ioe) {
      mCacheErrorLogger.logError(
          CacheErrorLogger.CacheErrorCategory.GENERIC_IO,
          TAG,
          "getResource",
          ioe);
      mCacheEventListener.onReadException();
      return null;
    }
  }