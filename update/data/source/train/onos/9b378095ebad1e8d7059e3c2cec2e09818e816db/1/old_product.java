@Override
    public OpticalConnectivityId setupConnectivity(ConnectPoint ingress, ConnectPoint egress,
                                                   Bandwidth bandwidth, Duration latency) {
        checkNotNull(ingress);
        checkNotNull(egress);
        log.info("setupConnectivity({}, {}, {}, {})", ingress, egress, bandwidth, latency);

        bandwidth = (bandwidth == null) ? NO_BW_REQUIREMENT : bandwidth;

        Set<Path> paths = pathService.getPaths(ingress.deviceId(), egress.deviceId(),
                new BandwidthLinkWeight(bandwidth));
        if (paths.isEmpty()) {
            log.warn("Unable to find multi-layer path.");
            return null;
        }

        // Search path with available cross connect points
        for (Path path : paths) {
            // Path service calculates from node to node, we're only interested in port to port
            if (!path.src().equals(ingress) || !path.dst().equals(egress)) {
                continue;
            }

            OpticalConnectivityId id = setupPath(path, bandwidth, latency);
            if (id != null) {
                log.info("Assigned OpticalConnectivityId: {}", id);
                return id;
            }
        }

        log.error("setupConnectivity({}, {}, {}, {}) failed.", ingress, egress, bandwidth, latency);

        return null;
    }