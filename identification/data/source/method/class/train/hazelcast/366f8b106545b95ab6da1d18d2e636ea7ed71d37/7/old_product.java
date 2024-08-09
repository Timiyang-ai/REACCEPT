public MapConfig setBackupCount(final int backupCount) {
        if ((backupCount < 0) || (backupCount > 3)) {
            throw new IllegalArgumentException("map backup count must be 0, 1, 2 or 3");
        }
        this.backupCount = backupCount;
        return this;
    }