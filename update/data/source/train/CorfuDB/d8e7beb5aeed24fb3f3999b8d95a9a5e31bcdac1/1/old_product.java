public boolean handleFailure(IReconfigurationHandlerPolicy failureHandlerPolicy,
                                 Layout currentLayout,
                                 CorfuRuntime corfuRuntime,
                                 Set<String> failedServers) {
        try {
            corfuRuntime.getLayoutManagementView().handleFailure(failureHandlerPolicy,
                    currentLayout, failedServers);
            return true;
        } catch (Exception e) {
            log.error("Error: handleFailure: {}", e);
            return false;
        }
    }