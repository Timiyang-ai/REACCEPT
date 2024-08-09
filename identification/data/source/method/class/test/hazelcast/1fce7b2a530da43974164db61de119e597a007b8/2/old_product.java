public boolean isSyncForced(BackupAwareOperation backupAwareOp) {
        if (disabled) {
            return false;
        }

        if (backupAwareOp.getPartitionId() < 0) {
            throw new IllegalArgumentException("A BackupAwareOperation can't have a negative partitionId, "
                    + backupAwareOp);
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

        int index = op.getPartitionId() * INTS_PER_CACHE_LINE;
        int oldSyncDelay = syncDelays[index];

        if (oldSyncDelay == 1) {
            int newSyncDelay = randomSyncDelay();
            syncDelays[index] = newSyncDelay;
            return true;
        }

        syncDelays[index] = oldSyncDelay - 1;
        return false;
    }