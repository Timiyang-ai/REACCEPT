boolean hasCapacity(Node dockerHost, Flavor flavor) {
        return freeCapacityOf(dockerHost, true, false).hasCapacityFor(flavor) && freeIPs(dockerHost) > 0;
    }