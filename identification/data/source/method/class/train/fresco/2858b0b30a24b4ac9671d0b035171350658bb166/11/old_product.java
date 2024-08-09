@Override
  public BinaryResource getResource(final CacheKey key) {
    String resourceId = null;
    SettableCacheEvent cacheEvent = new SettableCacheEvent()
        .setCacheKey(key);

    try {
      synchronized (mLock) {
        BinaryResource resource = null;
        if (mIndex.containsKey(key)) {
          resourceId = mIndex.get(key);
          cacheEvent.setResourceId(resourceId);
          resource = mStorage.getResource(resourceId, key);
        } else {
          List<String> resourceIds = getResourceIds(key);
          for (int i = 0; i < resourceIds.size(); i++) {
            resourceId = resourceIds.get(i);
            cacheEvent.setResourceId(resourceId);
            resource = mStorage.getResource(resourceId, key);
            if (resource != null) {
              break;
            }
          }
        }
        if (resource == null) {
          mCacheEventListener.onMiss(cacheEvent);
          mIndex.remove(key);
        } else {
          mCacheEventListener.onHit(cacheEvent);
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
      cacheEvent.setException(ioe);
      mCacheEventListener.onReadException(cacheEvent);
      return null;
    }
  }