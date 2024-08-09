private ResourceCapacity freeCapacityOf(Node dockerHost, boolean includeHeadroom) {
        ResourceCapacity hostCapacity = new ResourceCapacity(dockerHost);
        for (Node container : allNodes.childNodes(dockerHost).asList()) {
            // Until we have migrated we might have docker containers unallocated
            if (includeHeadroom || !(container.allocation().isPresent() && container.allocation().get().owner().tenant().value().equals("headroom"))) {
                hostCapacity.subtract(container);
            }
        }
        return hostCapacity;
    }