public ObjectCache acquire(final @NonNull String uniqueCacheIdentifier) {
        CacheIdentifier prefix = CACHE_IDS.get(uniqueCacheIdentifier);
        if (prefix == null) {
            prefix = new CacheIdentifier(CACHE_ID_SEQ.incrementAndGet());
            CacheIdentifier existing = CACHE_IDS.putIfAbsent(uniqueCacheIdentifier, prefix);
            if (existing != null) {
                prefix = existing;
            }
        }

        return CACHES.acquire(prefix);
    }