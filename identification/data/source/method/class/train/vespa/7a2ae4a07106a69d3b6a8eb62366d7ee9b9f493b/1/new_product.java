int freeIPs(Node dockerHost) {
        return dockerHost.ipAddressPool().findUnused(allNodes).size();
    }