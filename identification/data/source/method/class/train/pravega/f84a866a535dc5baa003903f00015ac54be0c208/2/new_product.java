void flush() {
        HashSet<Long> elements;
        synchronized (this.readIndex) {
            elements = this.recentStreamSegmentIds;
            this.recentStreamSegmentIds = new HashSet<>();
        }

        this.readIndex.triggerFutureReads(elements);
        if (this.flushCallback != null) {
            this.flushCallback.run();
        }
    }