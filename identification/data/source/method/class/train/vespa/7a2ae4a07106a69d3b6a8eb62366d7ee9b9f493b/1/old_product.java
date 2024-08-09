int freeIPs(Node dockerHost) {
        return findFreeIps(dockerHost, allNodes.asList()).size();
    }