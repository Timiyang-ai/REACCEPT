public CompletableFuture<Void> createNewStreamSegment(String streamSegmentName, Collection<AttributeUpdate> attributes, Duration timeout) {
        long traceId = LoggerHelpers.traceEnterWithContext(log, traceObjectId, "createNewStreamSegment", streamSegmentName);
        long segmentId = this.containerMetadata.getStreamSegmentId(streamSegmentName, true);
        if (isValidStreamSegmentId(segmentId)) {
            // Quick fail: see if this is an active Segment, and if so, don't bother with anything else.
            return FutureHelpers.failedFuture(new StreamSegmentExistsException(streamSegmentName));
        }

        TimeoutTimer timer = new TimeoutTimer(timeout);
        return this.storage
                .create(streamSegmentName, timer.getRemaining())
                .thenComposeAsync(si -> this.stateStore.put(streamSegmentName, getState(si, attributes), timer.getRemaining()), this.executor)
                .thenAccept(v -> LoggerHelpers.traceLeave(log, traceObjectId, "createNewStreamSegment", traceId, streamSegmentName));
    }