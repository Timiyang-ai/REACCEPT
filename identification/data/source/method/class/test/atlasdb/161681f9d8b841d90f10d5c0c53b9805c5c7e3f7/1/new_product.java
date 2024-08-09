@Override
    public void logCurrentState() {
        StringBuilder logString = getGeneralLockStats();
        log.info("Current State: {}", logString.toString());

        try {
            logAllHeldAndOutstandingLocks();
        } catch (IOException e) {
            log.error("Can't dump state to Yaml: [{}]", e);
            throw new IllegalStateException(e);
        }
    }