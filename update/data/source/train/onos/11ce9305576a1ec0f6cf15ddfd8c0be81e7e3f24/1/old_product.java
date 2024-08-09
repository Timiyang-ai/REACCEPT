public void isisDeactivate() {
        disconnectExecutor();
        processes = null;
        peerExecFactory.shutdown();
    }