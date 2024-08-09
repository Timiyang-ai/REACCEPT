public void setInterval(Quantum interval) {
        intervalLock.writeLock().lock();

        Quantum oldInterval = interval;

        try {
            oldInterval = this.interval;

            synchronized(pipeMap) {
                WeakReference<SchedulingPipe> oldPipeRef = pipeMap.get(oldInterval);
                WeakReference<SchedulingPipe> newPipeRef = pipeMap.get(interval);

                SchedulingPipe oldPipe = oldPipeRef != null ? oldPipeRef.get() : null;
                SchedulingPipe newPipe = newPipeRef != null ? newPipeRef.get() : null;
                if (oldPipe != null) {
                    oldPipe.removeTask(this);
                }
                if (newPipe == null && interval != Quantum.SUSPENDED) {
                    newPipe = new SchedulingPipe(interval);
                    pipeMap.put(interval, new WeakReference<SchedulingPipe>(newPipe));
                }
                if (newPipe != null) {
                    newPipe.addTask(this);
                }
            }
            this.interval = interval;
        } finally {
            intervalLock.writeLock().unlock();
        }

        pcs.firePropertyChange(INTERVAL_PROPERTY, oldInterval, interval);
    }