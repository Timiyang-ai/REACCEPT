void flush() {
        this.cacheUpdater.flush();
        if (this.flushCallback != null) {
            this.flushCallback.run();
        }
    }