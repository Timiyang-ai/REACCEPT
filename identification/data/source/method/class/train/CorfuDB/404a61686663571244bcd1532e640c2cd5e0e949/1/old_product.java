public void shutdown() {
        shutdown = true;
        getExecutor().shutdownNow();
    }