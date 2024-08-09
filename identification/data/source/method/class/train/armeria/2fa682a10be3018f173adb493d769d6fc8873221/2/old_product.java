static Backoff exponential(long minIntervalMillis, long maxIntervalMillis) {
        return exponential(minIntervalMillis, maxIntervalMillis, 2.0);
    }