public long getOrCompute(K key, LongSupplier compute) {
        if (free.compareAndSet(true, false)) {
            try {
                return cache.computeIfAbsent(key, k -> compute.getAsLong());
            } finally {
                free.set(true);
            }
        } else {
            return compute.getAsLong();
        }
    }