@Override public GridClientFuture<ClusterState> state(
        UUID destNodeId
    ) throws GridClientClosedException, GridClientConnectionResetException {
        return makeRequest(GridClientClusterStateRequest.currentState(), destNodeId);
    }