@VisibleForTesting
    public CompletableFuture<Long> getOrAssignStreamSegmentId(String streamSegmentName, Duration timeout) {
        return getOrAssignStreamSegmentId(streamSegmentName, timeout, CompletableFuture::completedFuture);
    }