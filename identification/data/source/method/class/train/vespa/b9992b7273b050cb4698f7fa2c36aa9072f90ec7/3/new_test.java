    private Optional<String> findSlobrokServiceName(String serviceType, String configId) {
        return slobrokMonitorManager.findSlobrokServiceName(
                new ServiceType(serviceType),
                new ConfigId(configId));
    }