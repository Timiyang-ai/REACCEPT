public CompletableFuture<RingbufferSlice<Map.Entry<Long, byte[]>>> readMetrics(long startSequence) {
        if (!config.isEnabled()) {
            throw new IllegalArgumentException("Metrics collection is not enabled");
        }
        CompletableFuture<RingbufferSlice<Map.Entry<Long, byte[]>>> future = new CompletableFuture<>();
        future.whenComplete(withTryCatch(logger, (s, e) -> pendingReads.remove(future)));
        pendingReads.put(future, startSequence);

        tryCompleteRead(future, startSequence);

        return future;
    }