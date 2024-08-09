public NodeResources freeCapacityOf(Node dockerHost, boolean includeInactive) {
        // Only hosts have free capacity
        if ( ! dockerHost.type().equals(NodeType.host)) return new NodeResources(0, 0, 0);

        return allNodes.childrenOf(dockerHost).asList().stream()
                .filter(container -> !(includeInactive && isInactiveOrRetired(container)))
                .map(host -> host.flavor().resources())
                .reduce(dockerHost.flavor().resources(), NodeResources::subtract);
    }