void commit(UpdateableContainerMetadata target) {
        Preconditions.checkArgument(target.getContainerId() == this.containerId, "ContainerId mismatch");
        Preconditions.checkArgument(target.isRecoveryMode() == this.isRecoveryMode(), "isRecoveryMode mismatch");

        if (target.isRecoveryMode()) {
            if (this.processedCheckpoint) {
                // If we processed a checkpoint during recovery, we need to wipe the metadata clean. We are setting
                // a brand new one.
                target.reset();
            }

            // RecoverableMetadata.reset() cleaned up the Operation Sequence number. We need to set it back to whatever
            // we have in our UpdateTransaction. If we have nothing, we'll just set it to the default.
            assert this.newSequenceNumber >= ContainerMetadata.INITIAL_OPERATION_SEQUENCE_NUMBER
                    : "Invalid Sequence Number " + this.newSequenceNumber;
            target.setOperationSequenceNumber(this.newSequenceNumber);
        }

        // Commit all segment-related transactional changes to their respective sources.
        this.segmentUpdates.values().forEach(txn -> {
            UpdateableSegmentMetadata targetSegmentMetadata = target.getStreamSegmentMetadata(txn.getId());
            if (targetSegmentMetadata == null) {
                targetSegmentMetadata = this.newSegments.get(txn.getId());
            }

            txn.apply(targetSegmentMetadata);
        });

        // Copy all Segment Metadata
        copySegmentMetadata(this.newSegments.values(), target);

        // Copy truncation points.
        this.newTruncationPoints.forEach(target::setValidTruncationPoint);

        // We are done. Clear the transaction.
        clear();
    }