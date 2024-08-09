synchronized List<Write> getWritesToExecute(long maximumAccumulatedSize) {
        Exceptions.checkNotClosed(this.closed, this);
        long accumulatedSize = 0;
        int activeWriteCount = 0;

        // Collect all remaining writes, as long as they are not currently in-progress and have the same ledger id
        // as the first item in the ledger.
        long firstLedgerId = this.writes.peekFirst().getWriteLedger().metadata.getLedgerId();
        boolean canSkip = true;

        List<Write> result = new ArrayList<>();
        for (Write write : this.writes) {
            if (accumulatedSize >= maximumAccumulatedSize || activeWriteCount >= this.maxParallelism) {
                // Either reached the throttling limit or ledger max size limit.
                // If we try to send too many writes to this ledger, the writes are likely to be rejected with
                // LedgerClosedException and simply be retried again.
                break;
            }

            // Account for this write's size, even if it's complete or in progress.
            accumulatedSize += write.data.getLength();
            if (write.isInProgress()) {
                activeWriteCount++;
                if (!canSkip) {
                    // We stumbled across an in-progress write after a not-in-progress write. We can't retry now.
                    // This is likely due to a bunch of writes failing (i.e. due to a LedgerClosedEx), but we overlapped
                    // with their updating their status. Try again next time (when that write completes).
                    return Collections.emptyList();
                }
            } else if (write.getWriteLedger().metadata.getLedgerId() != firstLedgerId) {
                // We cannot initiate writes in a new ledger until all writes in the previous ledger completed.
                break;
            } else if (!write.isDone()) {
                canSkip = false;
                result.add(write);
                activeWriteCount++;
            }
        }

        return result;
    }