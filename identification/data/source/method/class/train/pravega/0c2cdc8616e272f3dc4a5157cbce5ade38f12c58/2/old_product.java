synchronized boolean removeFinishedWrites() {
        long currentTime = this.timeSupplier.get();
        long totalElapsed = 0;
        int removedCount = 0;
        while (!this.writes.isEmpty() && this.writes.peekFirst().isDone()) {
            Write w = this.writes.removeFirst();
            this.totalLength = Math.max(0, this.totalLength - w.data.getLength());
            removedCount++;
            totalElapsed += currentTime - w.getTimestamp();
        }

        if (removedCount > 0) {
            this.lastDurationMillis = (int) (totalElapsed / removedCount / AbstractTimer.NANOS_TO_MILLIS);
        }

        return !this.writes.isEmpty();
    }