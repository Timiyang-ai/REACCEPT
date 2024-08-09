public ClusterState getAggregatedState() {
        if (clusterStates.isEmpty()) {
            throw new IllegalStateException("Insufficient cluster state");
        }

        if (clusterStates.size() == 1) {
            return clusterStates.get(0);
        }

        Map<String, NodeState> stateMap = clusterStates.stream()
                .map(ClusterState::getNodes)
                .reduce((prevNodes, currNodes) -> {
                    Map<String, NodeState> actualState = new HashMap<>();

                    for (String endpoint : currNodes.keySet()) {
                        NodeState prevNodeState = prevNodes.get(endpoint);
                        NodeState currNodeState = currNodes.get(endpoint);

                        //update the old node state by the new one
                        if (currNodeState.isConnected() || !prevNodeState.isConnected()) {
                            actualState.put(endpoint, currNodeState);
                        } else {
                            actualState.put(endpoint, prevNodeState);
                        }
                    }

                    return ImmutableMap.copyOf(actualState);
                }).orElse(ImmutableMap.of());

        return ClusterState.builder()
                .localEndpoint(localEndpoint)
                .unresponsiveNodes(unresponsiveNodes)
                .nodes(stateMap)
                .build();
    }