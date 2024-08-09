public T failureResponseLogLevel(LogLevel failedResponseLogLevel) {
        if (isResponseLogLevelMapperSet) {
            throw new IllegalStateException("responseLogLevelMapper has been set already.");
        }
        this.failedResponseLogLevel = requireNonNull(failedResponseLogLevel, "failedResponseLogLevel");
        isResponseLogLevelSet = true;
        return self();
    }