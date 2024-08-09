public QueueConfig setBackupCount(final int backupCount) {
        if (backupCount < MIN_BACKUP_COUNT) {
            throw new IllegalArgumentException("backup count must be equal to or bigger than "
                    + MIN_BACKUP_COUNT);
        }
        if ((backupCount + this.asyncBackupCount) > MAX_BACKUP_COUNT) {
            throw new IllegalArgumentException("total (sync + async) backup count must be less than "
                    + MAX_BACKUP_COUNT);
        }
        this.backupCount = backupCount;
        return this;
    }