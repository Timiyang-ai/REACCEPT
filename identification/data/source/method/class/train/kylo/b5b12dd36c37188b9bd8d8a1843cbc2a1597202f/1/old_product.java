@OnShutdown
    public void shutdown() {
        contextTimeoutTimer.cancel();
        for (TimeoutContext timeoutContext: timeoutContexts.values()) {
            deleteContext(timeoutContext.ContextName);
        }
    }