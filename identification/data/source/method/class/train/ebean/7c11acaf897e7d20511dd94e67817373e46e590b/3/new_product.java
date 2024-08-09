public ServerCache getCache(String cacheKey, ServerCacheType type) {

    ServerCache cache = concMap.get(cacheKey);
    if (cache != null) {
      return cache;
    }
    synchronized (monitor) {
      cache = synchMap.get(cacheKey);
      if (cache == null) {
        ServerCacheOptions options = getCacheOptions(cacheKey);
        cache = cacheFactory.createCache(type, cacheKey, options);
        synchMap.put(cacheKey, cache);
        concMap.put(cacheKey, cache);
      }
      return cache;
    }
  }