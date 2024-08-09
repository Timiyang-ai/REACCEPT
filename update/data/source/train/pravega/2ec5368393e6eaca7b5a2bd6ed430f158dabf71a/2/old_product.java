public void clear() {
        this.readIndex.clear();
        this.inMemoryOperationLog.clear();
        synchronized (this.readIndex) {
            this.recentStreamSegmentIds = new HashSet<>();
        }
    }