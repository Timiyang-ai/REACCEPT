public SettableHealthChecker setHealthy(boolean isHealthy) {
        final boolean oldValue = this.isHealthy.getAndSet(isHealthy);
        if (oldValue != isHealthy) {
            notifyListeners(this);
        }
        return this;
    }