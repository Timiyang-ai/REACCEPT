public void reset() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            /*
             * Retract generation number enough to cover threads
             * currently waiting on current and still resuming from
             * previous generation, plus similarly accommodating spans
             * after the reset.
             */
            generation -= 4;
            broken = false;
            trip.signalAll();
        } finally {
            lock.unlock();
        }
    }