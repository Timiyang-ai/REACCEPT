public void setInterval(Quantum interval) {
        intervalLock.writeLock().lock();

        Quantum oldInterval = interval;

        try {
            oldInterval = this.interval;
            this.interval = interval;
        } finally {
            intervalLock.writeLock().unlock();
        }

        pcs.firePropertyChange(INTERVAL_PROPERTY, oldInterval, interval);
    }