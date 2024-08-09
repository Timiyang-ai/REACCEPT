public TopicConfig setGlobalOrderingEnabled(boolean globalOrderingEnabled) {
        if (this.multiThreadingEnabled && globalOrderingEnabled) {
            throw new IllegalArgumentException("Global ordering can not be enabled when multi-threading is used.");
        }
        this.globalOrderingEnabled = globalOrderingEnabled;
        return this;
    }