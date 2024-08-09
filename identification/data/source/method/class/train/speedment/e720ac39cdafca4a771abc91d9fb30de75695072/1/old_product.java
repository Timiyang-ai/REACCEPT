public long getOrCompute(K key, LongSupplier compute) {
        final Long cached = cache.get(key);
        if (cached == null) {
            final long computed = compute.getAsLong();

            if (semaphore.tryAcquire()) {
                try {
                    cache.put(key, computed);
                } finally {
                    semaphore.release();
                }
            }

            return computed;
        } else {
            return cached;
        }
    }