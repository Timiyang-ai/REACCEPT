public CompletableFuture<Void> createNewStreamSegment(String streamSegmentName, Duration timeout) {
        long traceId = LoggerHelpers.traceEnter(log, traceObjectId, "createNewStreamSegment", streamSegmentName);
        long streamId = this.containerMetadata.getStreamSegmentId(streamSegmentName);
        if (isValidStreamSegmentId(streamId)) {
            return FutureHelpers.failedFuture(new StreamSegmentExistsException("Given StreamSegmentName is already registered internally. Most likely it already exists."));
        }

        // Create the StreamSegment, and then assign a Unique Internal Id to it.
        // Note: this is slightly sub-optimal, as we create the stream, but getOrAssignStreamSegmentId makes another call
        // to get the same info about the StreamSegmentId.
        TimeoutTimer timer = new TimeoutTimer(timeout);
        return this.storage
                .create(streamSegmentName, timer.getRemaining())
                .thenCompose(si -> getOrAssignStreamSegmentId(si.getName(), timer.getRemaining()))
                .thenAccept(id -> LoggerHelpers.traceLeave(log, traceObjectId, "createNewStreamSegment", traceId, streamSegmentName, id));
    }