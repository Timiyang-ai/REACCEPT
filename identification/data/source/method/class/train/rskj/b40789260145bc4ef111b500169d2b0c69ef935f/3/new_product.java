@VisibleForTesting
    public void startPunishment(long expirationTime) {
        if (!punishmentEnabled) {
            return;
        }

        try {
            rwlock.writeLock().lock();
            this.goodReputation = false;
            this.punishmentTime = expirationTime;
            this.punishmentCounter++;
            this.timeLostGoodReputation = System.currentTimeMillis();
        } finally {
            rwlock.writeLock().unlock();
        }
    }