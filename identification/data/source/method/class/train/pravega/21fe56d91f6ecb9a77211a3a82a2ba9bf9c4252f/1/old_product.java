public void flush() {
        HashSet<Long> elements;
        synchronized (this.cache) {
            elements = this.recentStreamSegmentIds;
            this.recentStreamSegmentIds = new HashSet<>();
        }

        this.cache.triggerFutureReads(elements);
    }