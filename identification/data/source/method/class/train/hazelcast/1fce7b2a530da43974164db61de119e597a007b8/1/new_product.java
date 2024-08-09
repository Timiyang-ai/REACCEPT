boolean isSyncForced(BackupAwareOperation backupAwareOp) {
        if (disabled) {
            return false;
        }

        // if there are no asynchronous backups, there is nothing to regulate.
        if (backupAwareOp.getAsyncBackupCount() == 0) {
            return false;
        }

        if (backupAwareOp instanceof UrgentSystemOperation) {
            return false;
        }

        for (; ; ) {
            int current = syncCountdown.decrementAndGet();
            if (current > 0) {
                return false;
            }

            if (syncCountdown.compareAndSet(current, randomSyncDelay())) {
                return true;
            }
        }
    }