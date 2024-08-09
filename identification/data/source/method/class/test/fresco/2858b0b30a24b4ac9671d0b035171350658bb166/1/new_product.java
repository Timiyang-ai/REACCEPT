@Override
  public BinaryResource getResource(final CacheKey key) {
    String resourceId = null;
    try {
      synchronized (mLock) {
        BinaryResource resource = null;
        if (mIndex.containsKey(key)) {
          resourceId = mIndex.get(key);
          resource = mStorage.getResource(resourceId, key);
        } else {
          List<String> resourceIds = getResourceIds(key);
          for (int i = 0; i < resourceIds.size(); i++) {
            resourceId = resourceIds.get(i);
            resource = mStorage.getResource(resourceId, key);
            if (resource != null) {
              break;
            }
          }
        }
        if (resource == null) {
          mCacheEventListener.onMiss(key, resourceId);
          mIndex.remove(key);
        } else {
          mCacheEventListener.onHit(key, resourceId);
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
      mCacheEventListener.onReadException(key, resourceId, ioe);
      return null;
    }
  }