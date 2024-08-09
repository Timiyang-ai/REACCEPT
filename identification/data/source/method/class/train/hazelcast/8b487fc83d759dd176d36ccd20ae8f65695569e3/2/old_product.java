public QueueConfig setAsyncBackupCount(final int asyncBackupCount) {
        if (asyncBackupCount < MIN_BACKUP_COUNT) {
            throw new IllegalArgumentException("async backup count must be equal to or bigger than "
                    + MIN_BACKUP_COUNT);
        }
        if ((this.backupCount + asyncBackupCount) > MAX_BACKUP_COUNT) {
            throw new IllegalArgumentException("total (sync + async) backup count must be less than "
                    + MAX_BACKUP_COUNT);
        }
        this.asyncBackupCount = asyncBackupCount;
        return this;
    }