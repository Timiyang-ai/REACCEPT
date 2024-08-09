public void shutdown() {
        if (!running) {
            return;
        }
        running = false;
        LOG.info("Shutting down " + getName());
        this.interrupt();
        try {
            this.join();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            LOG.warn("Interrupted while shutting down auditor bookie", ie);
        }
    }