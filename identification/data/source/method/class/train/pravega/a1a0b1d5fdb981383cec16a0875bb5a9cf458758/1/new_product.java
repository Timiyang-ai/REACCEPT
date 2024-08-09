public CompletableFuture<Long> getOrAssignStreamSegmentId(String streamSegmentName, Duration timeout) {
        // Check to see if the metadata already knows about this stream.
        long streamSegmentId = this.containerMetadata.getStreamSegmentId(streamSegmentName);
        if (isValidStreamSegmentId(streamSegmentId)) {
            // We already have a value, just return it (but make sure the Segment has not been deleted).
            if (this.containerMetadata.getStreamSegmentMetadata(streamSegmentId).isDeleted()) {
                return FutureHelpers.failedFuture(new StreamSegmentNotExistsException(streamSegmentName));
            } else {
                return CompletableFuture.completedFuture(streamSegmentId);
            }
        }

        // See if anyone else is currently waiting to get this StreamSegment's id.
        CompletableFuture<Long> result;
        boolean needsAssignment = false;
        synchronized (assignmentLock) {
            result = this.pendingRequests.getOrDefault(streamSegmentName, null);
            if (result == null) {
                needsAssignment = true;
                result = new CompletableFuture<>();
                this.pendingRequests.put(streamSegmentName, result);
            }
        }
        // We are the first/only ones requesting this id; go ahead and assign an id.
        if (needsAssignment) {
            // Determine if given StreamSegmentName is actually a batch StreamSegmentName.
            String parentStreamSegmentName = StreamSegmentNameUtils.getParentStreamSegmentName(streamSegmentName);
            if (parentStreamSegmentName == null) {
                // Stand-alone StreamSegment.
                this.executor.execute(() -> assignStreamSegmentId(streamSegmentName, timeout));
            } else {
                this.executor.execute(() -> assignBatchStreamSegmentId(streamSegmentName, parentStreamSegmentName, timeout));
            }
        }

        return result;
    }