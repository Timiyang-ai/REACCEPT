@Override
    public void close() {
        synchronized (this.reads) {
            if (this.closed) {
                return;
            }

            this.closed = true;
        }

        cancelAll();
    }