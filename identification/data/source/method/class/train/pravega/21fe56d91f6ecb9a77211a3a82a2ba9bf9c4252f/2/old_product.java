public void clear() {
        this.cache.clear();
        this.inMemoryOperationLog.clear();
        synchronized (this.cache) {
            this.recentStreamSegmentIds = new HashSet<>();
        }
    }