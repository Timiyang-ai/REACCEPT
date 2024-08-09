static Backoff exponential(long minIntervalMillis, long maxIntervalMillis, double multiplier) {
        return new ExponentialBackoff(minIntervalMillis, maxIntervalMillis, multiplier);
    }