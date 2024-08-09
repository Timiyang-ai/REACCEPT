private ResourceCapacity freeCapacityOf(Node dockerHost, boolean includeHeadroom) {
        // Only hosts have free capacity
        if (!dockerHost.type().equals(NodeType.host)) return new ResourceCapacity();

        ResourceCapacity hostCapacity = new ResourceCapacity(dockerHost);
        for (Node container : allNodes.childNodes(dockerHost).asList()) {
            // Until we have migrated we might have docker containers unallocated - TODO check off if headroom tenant is safe
            if (includeHeadroom || !(container.allocation().isPresent() && container.allocation().get().owner().tenant().value().equals("headroom"))) {
                hostCapacity.subtract(container);
            }
        }
        return hostCapacity;
    }