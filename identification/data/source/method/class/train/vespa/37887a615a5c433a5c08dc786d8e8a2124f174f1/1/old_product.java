boolean hasCapacity(Node dockerHost, Flavor flavor) {
        return freeCapacityOf(dockerHost, true).hasCapacityFor(flavor) && freeIPs(dockerHost) > 0;
    }