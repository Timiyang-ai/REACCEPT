public void shutdown() {
        setState(ServerState.SHUTDOWN);
        getExecutor().shutdownNow();
    }