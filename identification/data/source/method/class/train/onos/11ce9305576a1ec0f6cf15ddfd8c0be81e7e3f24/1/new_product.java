public void isisDeactivate() {
        disconnectExecutor();
        processes = null;
        if (peerExecFactory != null) {
            peerExecFactory.shutdown();
        }
    }