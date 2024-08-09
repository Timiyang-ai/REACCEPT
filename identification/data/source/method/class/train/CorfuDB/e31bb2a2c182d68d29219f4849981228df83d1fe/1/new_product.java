public boolean isReady() {
        if (nodes.isEmpty()) {
            log.error("Invalid ClusterState: is empty");
            return false;
        }

        if (!checkEpochs()) {
            log.info("ClusterState is not consistent: {}", nodes);
            return false;
        }

        //if at least one node is not ready then entire cluster is not ready to provide correct information
        for (NodeState nodeState : nodes.values()) {
            if (nodeState.getConnectivity().getType() == NodeConnectivityType.NOT_READY) {
                return false;
            }
        }

        return true;
    }