private void joinWhileDeactivate1(boolean startClient, boolean withNewCache, ClusterState state) throws Exception {
        joinWhileClusterStateChange(startClient, withNewCache, state, INACTIVE);
    }