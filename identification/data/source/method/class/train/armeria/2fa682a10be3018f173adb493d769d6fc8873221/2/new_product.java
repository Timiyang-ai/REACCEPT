static Backoff exponential(long initialDelayMillis, long maxDelayMillis) {
        return exponential(initialDelayMillis, maxDelayMillis, 2.0);
    }