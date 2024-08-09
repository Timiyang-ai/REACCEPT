public boolean isSyncForced(BackupAwareOperation backupAwareOp) {
        if (disabled) {
            return false;
        }

        // if there are no asynchronous backups, there is nothing to regulate.
        if (backupAwareOp.getAsyncBackupCount() == 0) {
            return false;
        }

        Operation op = (Operation) backupAwareOp;

        // we never apply back-pressure on urgent operations.
        if (op.isUrgent()) {
            return false;
        }

        AtomicInteger syncDelayCounter = syncDelayCounter(op);

        for (; ; ) {
            int prev = syncDelayCounter.get();
            int next;

            boolean syncForced;
            if (prev > 0) {
                next = prev - 1;
                syncForced = false;
            } else {
                next = randomSyncDelay();
                syncForced = true;
            }

            if (syncDelayCounter.compareAndSet(prev, next)) {
                return syncForced;
            }
        }
    }