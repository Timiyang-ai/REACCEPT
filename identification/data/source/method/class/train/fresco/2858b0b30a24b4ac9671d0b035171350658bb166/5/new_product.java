@Override
  public BinaryResource getResource(final CacheKey key) {
    String resourceId = null;
    SettableCacheEvent cacheEvent = SettableCacheEvent.obtain()
        .setCacheKey(key);
    try {
      synchronized (mLock) {
        BinaryResource resource = null;
        List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
        for (int i = 0; i < resourceIds.size(); i++) {
          resourceId = resourceIds.get(i);
          cacheEvent.setResourceId(resourceId);
          resource = mStorage.getResource(resourceId, key);
          if (resource != null) {
            break;
          }
        }
        if (resource == null) {
          mCacheEventListener.onMiss(cacheEvent);
          mResourceIndex.remove(resourceId);
        } else {
          mCacheEventListener.onHit(cacheEvent);
          mResourceIndex.add(resourceId);
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
    } finally {
      cacheEvent.recycle();
    }
  }