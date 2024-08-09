public MapConfig setBackupCount(final int backupCount) {
        if ((backupCount < MIN_BACKUP_COUNT) || (backupCount > MAX_BACKUP_COUNT)) {
            throw new IllegalArgumentException("map backup count must be 0, 1, 2 or 3");
        }
        this.backupCount = backupCount;
        return this;
    }