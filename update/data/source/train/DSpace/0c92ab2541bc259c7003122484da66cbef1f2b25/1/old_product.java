public Cache getCache(String cacheName, CacheConfig cacheConfig) {
        if (cacheName == null || "".equals(cacheName)) {
            throw new IllegalArgumentException("cacheName cannot be null or empty string");
        }

        // find the cache in the records if possible
        Cache cache = this.cacheRecord.get(cacheName);
        if (cache == null) {
            cache = this.getRequestMap().get(cacheName);
        }

        // handle provider
        if (getCacheProvider() != null) {
            if (cache == null 
                    && cacheConfig != null 
                    && ! CacheScope.REQUEST.equals(cacheConfig.getCacheScope()) ) {
                try {
                    cache = getCacheProvider().getCache(cacheName, cacheConfig);
                } catch (Exception e) {
                    log.warn("Failure in provider ("+getCacheProvider()+"): " + e.getMessage());
                }
            }
        }

        // no cache found so make one
        if (cache == null) {
            // create the cache type based on the cache config
            if (cacheConfig != null 
                    && CacheScope.REQUEST.equals(cacheConfig.getCacheScope()) ) {
                cache = instantiateMapCache(cacheName, cacheConfig);
            } else {
                cache = instantiateEhCache(cacheName, cacheConfig);
            }
        }
        return cache;
    }