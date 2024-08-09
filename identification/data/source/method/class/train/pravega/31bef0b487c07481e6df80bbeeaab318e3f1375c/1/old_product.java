public CompletableFuture<Void> createNewStreamSegment(String streamSegmentName, Collection<AttributeUpdate> attributes, Duration timeout) {
        long traceId = LoggerHelpers.traceEnterWithContext(log, traceObjectId, "createNewStreamSegment", streamSegmentName);
        long segmentId = this.containerMetadata.getStreamSegmentId(streamSegmentName, true);
        if (isValidStreamSegmentId(segmentId)) {
            return FutureHelpers.failedFuture(new StreamSegmentExistsException(streamSegmentName));
        }

        // Create the StreamSegment, and then assign a Unique Internal Id to it.
        // Note: this is slightly sub-optimal, as we create the stream, but getOrAssignStreamSegmentId makes another call
        // to get the same info about the StreamSegmentId.
        TimeoutTimer timer = new TimeoutTimer(timeout);
        return this.storage
                .create(streamSegmentName, timer.getRemaining())
                .thenComposeAsync(si -> {
                    si = attachAttributes(si, attributes);
                    return persistInDurableLog(si, timeout);
                }, this.executor)
                .thenAccept(id -> LoggerHelpers.traceLeave(log, traceObjectId, "createNewStreamSegment", traceId, streamSegmentName, id));
    }