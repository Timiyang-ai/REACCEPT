long includeExistingKey(long segmentId, UUID keyHash, long segmentOffset) {
        SegmentKeyCache cache;
        int generation;
        synchronized (this.segmentCaches) {
            generation = this.currentCacheGeneration;
            cache = this.segmentCaches.computeIfAbsent(segmentId, s -> new SegmentKeyCache(s, this.cache));
        }

        return cache.includeExistingKey(keyHash, segmentOffset, generation);
    }