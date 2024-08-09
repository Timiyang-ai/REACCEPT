public void shutdown() {
        unregisterActivators();
        for (ServiceManagerSystem sms : serviceManagers) {
            try {
                sms.shutdown();
            } catch (Exception e) {
                // shutdown failures are not great but should NOT cause an interruption of processing
                System.err.println("Failure shutting down service manager ("+sms+"): " + e.getMessage());
            }
        }
        this.running = false; // wait til the end
        this.serviceManagers.clear();
        this.serviceMixinManager.clear();
        this.primaryServiceManager = null;
        log.info("Shutdown DSpace core service manager");
    }