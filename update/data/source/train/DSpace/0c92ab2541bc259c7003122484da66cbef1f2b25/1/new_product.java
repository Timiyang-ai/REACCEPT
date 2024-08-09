public Cache getCache(String cacheName, CacheConfig cacheConfig) {
        Cache cache = null;

        if (cacheName == null || "".equals(cacheName)) {
            throw new IllegalArgumentException("cacheName cannot be null or empty string");
        }

        if (cacheConfig != null && CacheScope.REQUEST.equals(cacheConfig.getCacheScope()) ) {
            Map<String, MapCache> caches = getRequestCaches();
            if (caches != null) {
                cache = caches.get(cacheName);
            }

            if (cache == null) {
                cache = instantiateMapCache(cacheName, cacheConfig);
            }
        } else {
            // find the cache in the records if possible
            cache = this.cacheRecord.get(cacheName);

            if (cache == null) {
                // handle provider
                if (getCacheProvider() != null) {
                    try {
                        cache = getCacheProvider().getCache(cacheName, cacheConfig);
                    } catch (Exception e) {
                        log.warn("Failure in provider ("+getCacheProvider()+"): " + e.getMessage());
                    }
                }
            }

            if (cache == null) {
                cache = instantiateEhCache(cacheName, cacheConfig);
            }
        }

        return cache;
    }