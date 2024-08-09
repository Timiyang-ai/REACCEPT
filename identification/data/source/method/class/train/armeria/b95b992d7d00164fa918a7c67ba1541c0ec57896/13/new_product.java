public T successfulResponseLogLevel(LogLevel successfulResponseLogLevel) {
        if (isResponseLogLevelMapperSet) {
            throw new IllegalStateException("responseLogLevelMapper has been set already.");
        }
        this.successfulResponseLogLevel =
                requireNonNull(successfulResponseLogLevel, "successfulResponseLogLevel");
        isResponseLogLevelSet = true;
        return self();
    }