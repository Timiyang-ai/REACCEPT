synchronized CleanupResult removeFinishedWrites() {
        Exceptions.checkNotClosed(this.closed, this);
        long currentTime = this.timeSupplier.get();
        long totalElapsed = 0;
        int removedCount = 0;
        boolean failedWrite = false;
        while (!this.writes.isEmpty() && this.writes.peekFirst().isDone()) {
            Write w = this.writes.removeFirst();
            this.totalLength = Math.max(0, this.totalLength - w.data.getLength());
            removedCount++;
            totalElapsed += currentTime - w.getQueueAddedTimestamp();
            failedWrite |= w.getFailureCause() != null;
        }

        if (removedCount > 0) {
            this.lastDurationMillis = (int) (totalElapsed / removedCount / AbstractTimer.NANOS_TO_MILLIS);
        }

        CleanupStatus status = failedWrite
                ? CleanupStatus.WriteFailed
                : this.writes.isEmpty() ? CleanupStatus.QueueEmpty : CleanupStatus.QueueNotEmpty;
        return new CleanupResult(status, removedCount);
    }