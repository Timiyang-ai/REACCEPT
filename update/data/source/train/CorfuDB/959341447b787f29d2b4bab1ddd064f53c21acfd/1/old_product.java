public boolean handleFailure(IFailureHandlerPolicy failureHandlerPolicy,
                              Layout currentLayout,
                              CorfuRuntime corfuRuntime,
                              Set<String> failedServers,
                              Set<String> healedServers) {
        try {
            corfuRuntime.getLayoutManagementView().handleFailure(failureHandlerPolicy,
                    currentLayout, failedServers, healedServers);
            return true;
        } catch (Exception e) {
            log.error("Error: handleFailure: {}", e);
            return false;
        }
    }