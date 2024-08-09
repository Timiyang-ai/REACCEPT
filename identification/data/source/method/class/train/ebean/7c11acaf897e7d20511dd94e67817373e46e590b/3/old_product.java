public ServerCache getCache(String cacheKey) {

    ServerCache cache = concMap.get(cacheKey);
    if (cache != null) {
      return cache;
    }
    synchronized (monitor) {
      cache = synchMap.get(cacheKey);
      if (cache == null) {
        ServerCacheOptions options = getCacheOptions(cacheKey);
        cache = cacheFactory.createCache(cacheKey, options);
        synchMap.put(cacheKey, cache);
        concMap.put(cacheKey, cache);
      }
      return cache;
    }
  }