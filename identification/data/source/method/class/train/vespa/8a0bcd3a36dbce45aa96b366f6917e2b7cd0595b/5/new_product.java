public ResourceCapacity freeCapacityOf(Node dockerHost, boolean treatInactiveOrRetiredAsUnusedCapacity) {
        // Only hosts have free capacity
        if (!dockerHost.type().equals(NodeType.host)) return ResourceCapacity.NONE;

        return allNodes.childrenOf(dockerHost).asList().stream()
                .filter(container -> !(treatInactiveOrRetiredAsUnusedCapacity && isInactiveOrRetired(container)))
                .map(ResourceCapacity::of)
                .reduce(ResourceCapacity.of(dockerHost), ResourceCapacity::subtract);
    }