CompletableFuture<SegmentProperties> getStreamSegmentInfo(String streamSegmentName, Duration timeout) {
        long streamSegmentId = this.containerMetadata.getStreamSegmentId(streamSegmentName, true);
        CompletableFuture<SegmentProperties> result;
        if (isValidStreamSegmentId(streamSegmentId)) {
            // Looks like the Segment is active and we have it in our Metadata. Return the result from there.
            SegmentMetadata sm = this.containerMetadata.getStreamSegmentMetadata(streamSegmentId);
            if (sm.isDeleted()) {
                result = FutureHelpers.failedFuture(new StreamSegmentNotExistsException(streamSegmentName));
            } else {
                result = CompletableFuture.completedFuture(sm.getSnapshot());
            }
        } else {
            // The Segment is not yet active.
            // First, check to see if we have a pending assignment. If so, piggyback on that.
            QueuedCallback<SegmentProperties> queuedCallback = checkConcurrentAssignment(streamSegmentName,
                    id -> CompletableFuture.completedFuture(this.containerMetadata.getStreamSegmentMetadata(id).getSnapshot()));

            if (queuedCallback != null) {
                result = queuedCallback.result;
            } else {
                // Not in metadata and no concurrent assignments. Go to Storage and get what's needed.
                TimeoutTimer timer = new TimeoutTimer(timeout);
                result = this.storage
                        .getStreamSegmentInfo(streamSegmentName, timer.getRemaining())
                        .thenComposeAsync(si -> retrieveAttributes(si, timer.getRemaining()), this.executor)
                        .thenApply(si -> si.properties);
            }
        }

        return result;
    }