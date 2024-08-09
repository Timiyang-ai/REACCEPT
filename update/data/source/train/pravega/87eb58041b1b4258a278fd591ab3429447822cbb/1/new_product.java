<T> CompletableFuture<T> getOrAssignStreamSegmentId(String streamSegmentName, Duration timeout, Function<Long, CompletableFuture<T>> thenCompose) {
        // Check to see if the metadata already knows about this Segment.
        Preconditions.checkNotNull(thenCompose, "thenCompose");
        long streamSegmentId = this.containerMetadata.getStreamSegmentId(streamSegmentName, true);
        if (isValidStreamSegmentId(streamSegmentId)) {
            // We already have a value, just return it (but make sure the Segment has not been deleted).
            if (this.containerMetadata.getStreamSegmentMetadata(streamSegmentId).isDeleted()) {
                return FutureHelpers.failedFuture(new StreamSegmentNotExistsException(streamSegmentName));
            } else {
                // Even though we have the value in the metadata, we need to be very careful not to invoke this callback
                // before any other existing callbacks are invoked. As such, verify if we have an existing PendingRequest
                // for this segment - if so, tag onto it so we invoke these callbacks in the correct order.
                QueuedCallback<T> queuedCallback = checkConcurrentAssignment(streamSegmentName, thenCompose);
                return queuedCallback == null ? thenCompose.apply(streamSegmentId) : queuedCallback.result;
            }
        }

        // See if anyone else is currently waiting to get this StreamSegment's id.
        QueuedCallback<T> queuedCallback;
        boolean needsAssignment = false;
        synchronized (this.assignmentLock) {
            PendingRequest pendingRequest = this.pendingRequests.getOrDefault(streamSegmentName, null);
            if (pendingRequest == null) {
                needsAssignment = true;
                pendingRequest = new PendingRequest();
                this.pendingRequests.put(streamSegmentName, pendingRequest);
            }

            queuedCallback = new QueuedCallback<>(thenCompose);
            pendingRequest.callbacks.add(queuedCallback);
        }

        // We are the first/only ones requesting this id; go ahead and assign an id.
        if (needsAssignment) {
            // Determine if given StreamSegmentName is actually a Transaction StreamSegmentName.
            String parentStreamSegmentName = StreamSegmentNameUtils.getParentStreamSegmentName(streamSegmentName);
            if (parentStreamSegmentName == null) {
                // Stand-alone StreamSegment.
                this.executor.execute(() -> assignStreamSegmentId(streamSegmentName, timeout));
            } else {
                this.executor.execute(() -> assignTransactionStreamSegmentId(streamSegmentName, parentStreamSegmentName, timeout));
            }
        }

        return queuedCallback.result;
    }