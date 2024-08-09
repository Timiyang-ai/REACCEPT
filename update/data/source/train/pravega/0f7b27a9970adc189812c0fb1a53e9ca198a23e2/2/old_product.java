public CompletableFuture<Boolean> truncate(long upToTransactionId, java.time.Duration timeout) {
        ensureNotClosed();
        Preconditions.checkState(this.logManager != null, "LogHandle is not initialized.");

        log.info("{}: Truncate (TransactionId = {}.", this.logName, upToTransactionId);
        throw new NotImplementedException("Truncate has not yet been implemented.");
    }