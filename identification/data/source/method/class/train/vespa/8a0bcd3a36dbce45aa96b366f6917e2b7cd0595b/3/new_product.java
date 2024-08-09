public ResourceCapacity freeCapacityOf(Node dockerHost, boolean retiredAsFreeCapacity) {
        // Only hosts have free capacity
        if (!dockerHost.type().equals(NodeType.host)) return new ResourceCapacity();

        ResourceCapacity hostCapacity = new ResourceCapacity(dockerHost);
        for (Node container : allNodes.childNodes(dockerHost).asList()) {
            if (retiredAsFreeCapacity && container.allocation().isPresent()
                    && container.allocation().get().membership().retired()) continue;
            hostCapacity.subtract(container);
        }
        return hostCapacity;
    }