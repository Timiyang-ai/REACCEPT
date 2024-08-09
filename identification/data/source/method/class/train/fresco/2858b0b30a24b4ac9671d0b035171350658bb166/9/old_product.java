@Override
  public BinaryResource getResource(final CacheKey key) {
    String resourceId = null;
    SettableCacheEvent cacheEvent = new SettableCacheEvent()
        .setCacheKey(key);
    Integer hashKey = Integer.valueOf(key.hashCode());

    try {
      synchronized (mLock) {
        BinaryResource resource = null;
        if (mIndex.containsKey(hashKey)) {
          resourceId = mIndex.get(hashKey);
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
          mIndex.remove(hashKey);
        } else {
          mCacheEventListener.onHit(cacheEvent);
          mIndex.put(hashKey, resourceId);
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