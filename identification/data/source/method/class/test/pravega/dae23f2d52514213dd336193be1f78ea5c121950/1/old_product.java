void add(StorageOperation operation) throws DataCorruptionException {
        ensureInitializedAndNotClosed();

        // Verify operation Segment Id.
        checkSegmentId(operation);

        // Verify operation offset (this also takes care of extra operations after Seal or Merge; no need for further checks).
        checkOffset(operation);

        // Add operation to list
        this.operations.addLast(operation);
        if (operation instanceof MergeBatchOperation) {
            this.mergeBatchCount++;
        } else if (operation instanceof StreamSegmentSealOperation) {
            this.hasSealPending = true;
        }

        // Update current state (note that MergeBatchOperations have a length of 0 if added to the BatchStreamSegment - because they don't have any effect on it).
        this.outstandingLength += operation.getLength();
        this.lastAddedOffset = operation.getStreamSegmentOffset() + operation.getLength();
    }