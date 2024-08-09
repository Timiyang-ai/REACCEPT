default CompletableFuture<SegmentProperties> create(String streamSegmentName, Duration timeout) {
        return create(streamSegmentName, SegmentRollingPolicy.NO_ROLLING, timeout);
    }