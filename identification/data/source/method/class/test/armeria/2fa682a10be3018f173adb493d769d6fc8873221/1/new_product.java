static Backoff fixed(long delayMillis) {
        return new FixedBackoff(delayMillis);
    }