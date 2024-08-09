private ResourceCapacity freeCapacityOf(Node dockerHost, boolean includeHeadroom) {
        // Only hosts have free capacity
        if (!dockerHost.type().equals(NodeType.host)) return new ResourceCapacity();

        ResourceCapacity hostCapacity = new ResourceCapacity(dockerHost);
        for (Node container : allNodes.childNodes(dockerHost).asList()) {
            if (includeHeadroom || !(container.allocation().isPresent() && container.allocation().get().owner().tenant().value().equals(HEADROOM_TENANT))) {
                hostCapacity.subtract(container);
            }
        }
        return hostCapacity;
    }