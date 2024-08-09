public boolean handleFailure(@Nonnull IReconfigurationHandlerPolicy failureHandlerPolicy,
                                 @Nonnull Layout currentLayout,
                                 @Nonnull CorfuRuntime corfuRuntime,
                                 @Nonnull Set<String> failedServers) {
        try {
            corfuRuntime.getLayoutManagementView().handleFailure(failureHandlerPolicy,
                    currentLayout, failedServers);
            return true;
        } catch (Exception e) {
            log.error("Error: handleFailure: {}", e);
            return false;
        }
    }