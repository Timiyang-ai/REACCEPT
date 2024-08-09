public T failureResponseLogLevel(LogLevel failedResponseLogLevel) {
        this.failedResponseLogLevel = requireNonNull(failedResponseLogLevel, "failedResponseLogLevel");
        return self();
    }