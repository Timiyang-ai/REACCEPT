@Override
  public BinaryResource getResource(final CacheKey key) {
    try {
      synchronized (mLock) {
        String resourceId = getResourceId(key);
        BinaryResource resource = mStorage.getResource(resourceId, key);
        if (resource == null) {
          mCacheEventListener.onMiss();
          mIndex.remove(key);
        } else {
          mCacheEventListener.onHit();
          mIndex.put(key, resourceId);
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