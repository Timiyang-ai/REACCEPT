@OnShutdown
    public void shutdown() {
        contextTimeoutTimer.cancel();
        for (SparkContextState sparkContextState: sparkContextsStates.values()) {
            deleteContext(sparkContextState.ContextName);
        }
    }