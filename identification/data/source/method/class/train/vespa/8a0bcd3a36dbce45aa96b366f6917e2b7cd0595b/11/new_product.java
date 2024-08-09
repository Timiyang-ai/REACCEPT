NodeResources freeCapacityOf(Node host, boolean excludeInactive) {
        // Only hosts have free capacity
        if (host.type() != NodeType.host) return new NodeResources(0, 0, 0, 0);
        NodeResources hostResources = hostResourcesCalculator.availableCapacityOf(host.flavor().resources());

        // Subtract used resources without taking disk speed into account since existing allocations grandfathered in
        // may not use reflect the actual disk speed (as of May 2019). This (the 3 diskSpeed assignments below)
        // can be removed when all node allocations accurately reflect the true host disk speed
        return allNodes.childrenOf(host).asList().stream()
                .filter(node -> !(excludeInactive && isInactiveOrRetired(node)))
                .map(node -> node.flavor().resources().anySpeed())
                .reduce(hostResources.anySpeed(), NodeResources::subtract)
                .withDiskSpeed(host.flavor().resources().diskSpeed());
    }