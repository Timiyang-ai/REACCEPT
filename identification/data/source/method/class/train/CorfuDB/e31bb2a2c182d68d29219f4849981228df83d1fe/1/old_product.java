public boolean isReady() {
        if (nodes.isEmpty()) {
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