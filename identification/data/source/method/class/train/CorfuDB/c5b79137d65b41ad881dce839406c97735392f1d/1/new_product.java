public void shutdown() {
        setState(ServerState.SHUTDOWN);
        getExecutors().forEach(ExecutorService::shutdownNow);
    }