protected MapCache instantiateMapCache(String cacheName, CacheConfig cacheConfig) {
        if (log.isDebugEnabled())
            log.debug("instantiateMapCache(String " + cacheName + ")");

        if (cacheName == null || "".equals(cacheName)) {
            throw new IllegalArgumentException("String cacheName must not be null or empty!");
        }

        // check for existing cache
        MapCache cache = null;
        CacheScope scope = CacheScope.REQUEST;
        if (cacheConfig != null) {
            scope = cacheConfig.getCacheScope();
        }

        Map<String, MapCache> caches = getRequestCaches();
        if (caches != null) {
            if (CacheScope.REQUEST.equals(scope)) {
                cache = caches.get(cacheName);
            }

            if (cache == null) {
                cache = new MapCache(cacheName, cacheConfig);
                // place cache into the right TL
                if (CacheScope.REQUEST.equals(scope)) {
                    caches.put(cacheName, cache);
                }
            }
        }

        return cache;
    }