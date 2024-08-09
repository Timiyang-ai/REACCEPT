private void joinWhileActivate1(boolean startClient, boolean withNewCache, ClusterState state) throws Exception {
        joinWhileClusterStateChange(startClient, withNewCache, INACTIVE, state);
    }