public T successfulResponseLogLevel(LogLevel successfulResponseLogLevel) {
        this.successfulResponseLogLevel =
                requireNonNull(successfulResponseLogLevel, "successfulResponseLogLevel");
        return self();
    }