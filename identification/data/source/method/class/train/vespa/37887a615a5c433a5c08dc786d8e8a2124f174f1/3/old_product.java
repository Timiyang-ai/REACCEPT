boolean hasCapacity(Node dockerHost, ResourceCapacity requestedCapacity) {
        return freeCapacityOf(dockerHost, false).hasCapacityFor(requestedCapacity) && freeIPs(dockerHost) > 0;
    }