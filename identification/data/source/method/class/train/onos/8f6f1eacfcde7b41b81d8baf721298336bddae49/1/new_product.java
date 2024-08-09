void shutdown() {
        synchronized (this) {
            timeout.cancel(true);
            timeout = null;
        }
    }