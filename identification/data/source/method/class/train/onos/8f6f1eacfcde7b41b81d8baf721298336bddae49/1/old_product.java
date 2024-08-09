void shutdown() {
        synchronized (this) {
            timeout.cancel();
            timeout = null;
        }
    }