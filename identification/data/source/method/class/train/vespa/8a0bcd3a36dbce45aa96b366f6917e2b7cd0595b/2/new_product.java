NodeResources freeCapacityOf(Node dockerHost, boolean excludeInactive) {
        // Only hosts have free capacity
        if (dockerHost.type() != NodeType.host) return new NodeResources(0, 0, 0, 0);
        NodeResources hostResources = hostResourcesCalculator.availableCapacityOf(dockerHost.flavor().resources());

        // Subtract used resources without taking disk speed into account since existing allocations grandfathered in
        // may not use reflect the actual disk speed (as of May 2019). This (the 3 diskSpeed assignments below)
        // can be removed when all node allocations accurately reflect the true host disk speed
        return allNodes.childrenOf(dockerHost).asList().stream()
                .filter(node -> !(excludeInactive && isInactiveOrRetired(node)))
                .map(node -> node.flavor().resources().withDiskSpeed(NodeResources.DiskSpeed.any))
                .reduce(hostResources.withDiskSpeed(NodeResources.DiskSpeed.any), NodeResources::subtract)
                .withDiskSpeed(dockerHost.flavor().resources().diskSpeed());
    }