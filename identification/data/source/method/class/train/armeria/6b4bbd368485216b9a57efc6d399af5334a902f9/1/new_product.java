protected final CircuitBreakerStrategy strategy() {
        checkState(strategy != null, "strategy is not set.");
        return strategy;
    }