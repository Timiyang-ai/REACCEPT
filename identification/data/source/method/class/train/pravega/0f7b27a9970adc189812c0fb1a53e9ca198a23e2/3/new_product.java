CompletableFuture<Boolean> truncate(DLSNAddress upToAddress, java.time.Duration timeout) {
        ensureNotClosed();
        Preconditions.checkState(this.logManager != null, "LogHandle is not initialized.");

        log.info("{}: Truncate (LogAddress = {}.", this.logName, upToAddress);
        Future<Boolean> truncateFuture = this.logWriter.truncate(upToAddress.getDLSN());
        return toCompletableFuture(truncateFuture, b -> b);
    }