protected boolean isAttached(String iface) {
        return ifaces.stream().anyMatch(iface::equals);
    }