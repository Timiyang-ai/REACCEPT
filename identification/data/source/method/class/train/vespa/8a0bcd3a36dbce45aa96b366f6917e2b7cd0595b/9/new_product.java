public ResourceCapacity freeCapacityOf(Node dockerHost, boolean treatInactiveOrRetiredAsUnusedCapacity) {
        // Only hosts have free capacity
        if (!dockerHost.type().equals(NodeType.host)) return new ResourceCapacity();

        ResourceCapacity hostCapacity = new ResourceCapacity(dockerHost);
        for (Node container : allNodes.childNodes(dockerHost).asList()) {
            boolean isUsedCapacity = !(treatInactiveOrRetiredAsUnusedCapacity && isInactiveOrRetired(container));
            if (isUsedCapacity) {
                hostCapacity.subtract(container);
            }
        }
        return hostCapacity;
    }