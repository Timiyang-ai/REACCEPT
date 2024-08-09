public List<T> close() {
        lock.lock();
        try {
            if (!this.closed) {
                this.closed = true;
                this.notEmpty.signal();
                return swapContents();
            }
        } finally {
            lock.unlock();
        }

        return new ArrayList<>();
    }