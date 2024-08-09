Optional<String> findSlobrokServiceName(ServiceType serviceType, ConfigId configId) {
        switch (serviceType.s()) {
            case "adminserver":
            case "config-sentinel":
            case "configproxy":
            case "configserver":
            case "logd":
            case "logserver":
            case "metricsproxy":
            case "slobrok":
            case "transactionlogserver":
                return Optional.empty();

            case "topleveldispatch":
                return Optional.of(configId.s());

            case "qrserver":
            case "container":
            case "container-clustercontroller":
            case "logserver-container":
            case "metricsproxy-container":
                return Optional.of("vespa/service/" + configId.s());

            case "searchnode": //TODO: handle only as storagenode instead of both as searchnode/storagenode
                return Optional.of(configId.s() + "/realtimecontroller");
            case "distributor":
            case "storagenode":
                return Optional.of("storage/cluster." + configId.s());
            default:
                logger.log(LogLevel.DEBUG, "Unknown service type " + serviceType.s() +
                        " with config id " + configId.s());
                return Optional.empty();
        }
    }