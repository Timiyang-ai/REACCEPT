static Backoff fixed(long intervalMillis) {
        return new FixedBackoff(intervalMillis);
    }