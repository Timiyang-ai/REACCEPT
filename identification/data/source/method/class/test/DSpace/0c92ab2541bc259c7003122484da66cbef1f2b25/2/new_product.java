@Override
    public void shutdown() {
        unregisterActivators();
        for (ServiceManagerSystem sms : serviceManagers) {
            try {
                sms.shutdown();
            } catch (Exception e) {
                // shutdown failures are not great but should NOT cause an interruption of processing
                log.error("Failure shutting down service manager ("+sms+"): " + e.getMessage(), e);
            }
        }
        this.running = false; // wait til the end
        this.serviceManagers.clear();
        this.primaryServiceManager = null;
        log.info("Shutdown DSpace core service manager");
    }