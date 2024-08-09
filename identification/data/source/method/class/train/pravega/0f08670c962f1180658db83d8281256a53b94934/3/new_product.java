void includeTailCache(long segmentId, Map<UUID, CacheBucketOffset> keyOffsets) {
        SegmentKeyCache cache;
        int generation;
        synchronized (this.segmentCaches) {
            generation = this.currentCacheGeneration;
            cache = this.segmentCaches.computeIfAbsent(segmentId, s -> new SegmentKeyCache(s, this.cacheStorage));
        }

        cache.includeTailCache(keyOffsets, generation);
    }