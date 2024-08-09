@Override
    public void close() {
        if (!this.closed) {
            this.closed = true;
            lock.lock();
            try {
                this.notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
    }