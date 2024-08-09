boolean hasCapacity(Node dockerHost, Flavor flavor) {
        return freeCapacityOf(dockerHost, false).hasCapacityFor(flavor) && freeIPs(dockerHost) > 0;
    }