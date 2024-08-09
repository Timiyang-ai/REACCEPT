void commit(DataFrameBuilder.CommitArgs commitArgs) {
            assert commitArgs.key() >= 0 : "DataFrameBuilder.CommitArgs does not have a key set";
            log.debug("{}: CommitSuccess ({}).", traceObjectId, commitArgs);

            List<CompletableOperation> toComplete = new ArrayList<>();
            Map<CompletableOperation, Throwable> toFail = new HashMap<>();
            try {
                // Record the end of a frame in the DurableDataLog directly into the base metadata. No need for locking here,
                // as the metadata has its own.
                OperationProcessor.this.metadata.recordTruncationMarker(commitArgs.getLastStartedSequenceNumber(), commitArgs.getLogAddress());
                final long lastOperationSequence = commitArgs.getLastFullySerializedSequenceNumber();
                final long addressSequence = commitArgs.getLogAddress().getSequence();

                synchronized (stateLock) {
                    if (addressSequence <= this.highestCommittedDataFrame) {
                        // Ack came out of order (we already processed one with a higher SeqNo).
                        log.debug("{}: CommitRejected ({}, HighestCommittedDataFrame = ).", traceObjectId, commitArgs, this.highestCommittedDataFrame);
                        return;
                    }

                    // Commit any changes to the metadata.
                    boolean checkpointExists = this.metadataTransactions.removeLessThanOrEqual(commitArgs);
                    assert checkpointExists : "No Metadata UpdateTransaction found for " + commitArgs;
                    OperationProcessor.this.metadataUpdater.commit(commitArgs.key());

                    // Acknowledge all pending entries, in the order in which they are in the queue (ascending seq no).
                    while (!this.pendingOperations.isEmpty()
                            && this.pendingOperations.peekFirst().getOperation().getSequenceNumber() <= lastOperationSequence) {
                        CompletableOperation op = this.pendingOperations.pollFirst();
                        try {
                            this.logUpdater.process(op.getOperation());
                        } catch (Throwable ex) {
                            // MemoryStateUpdater.process() should only throw DataCorruptionExceptions, but just in case it
                            // throws something else (i.e. NullPtr), we still need to handle it.
                            // First, fail the operation, since it has already been taken off the pending list.
                            log.error("{}: OperationCommitFailure ({}). {}", traceObjectId, op.getOperation(), ex);
                            toFail.put(op, ex);

                            // Then fail the remaining operations (which also handles fatal errors) and bail out.
                            collectFailureCandidates(ex, commitArgs, toFail);
                            if (isFatalException(ex)) {
                                CallbackHelpers.invokeSafely(OperationProcessor.this::errorHandler, ex, null);
                            }

                            return;
                        }

                        Throwable stopException = OperationProcessor.this.getStopException();
                        if (stopException != null) {
                            // Even if the operation succeeded, we encountered a stop exception (fatal failure) and most likely
                            // this operation will be corrupted in the DurableDataLog. Do not ack it as success. If the owning
                            // Container does end up recovering successfully, it's up to the client to figure out the state
                            // of the affected segments so it can resume operations.
                            toFail.put(op, stopException);
                        } else {
                            toComplete.add(op);
                        }
                    }

                    this.highestCommittedDataFrame = addressSequence;
                }

                this.logUpdater.flush();
            } finally {
                toComplete.forEach(CompletableOperation::complete);
                toFail.forEach(this::failOperation);
                autoCompleteIfNeeded();
                if (toFail.size() == 0) {
                    // Only record the commit if we had no failures.
                    this.checkpointPolicy.recordCommit(commitArgs.getDataFrameLength());
                }
            }
        }