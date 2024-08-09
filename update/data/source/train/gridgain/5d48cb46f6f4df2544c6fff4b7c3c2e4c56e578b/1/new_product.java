@Override public GridClientFuture<ClusterState> state(
        UUID destNodeId
    ) throws GridClientClosedException, GridClientConnectionResetException {
        if (nodeSupports(destNodeId, CLUSTER_READ_ONLY_MODE))
            return makeRequest(GridClientClusterStateRequest.currentState(), destNodeId);
        else {
            // Backward compatibility
            GridClientFutureAdapter<ClusterState> resFut = new GridClientFutureAdapter<>();

            currentState(destNodeId).listen(fut -> {
                try {
                    resFut.onDone(fut.get() ? ClusterState.ACTIVE : ClusterState.INACTIVE);
                }
                catch (GridClientException e) {
                    resFut.onDone(e);
                }
            });

            return resFut;
        }
    }