@Override
    public OpticalConnectivityId setupConnectivity(ConnectPoint ingress, ConnectPoint egress,
                                                   Bandwidth bandwidth, Duration latency) {
        checkNotNull(ingress);
        checkNotNull(egress);
        log.info("setupConnectivity({}, {}, {}, {})", ingress, egress, bandwidth, latency);

        Bandwidth bw = (bandwidth == null) ? NO_BW_REQUIREMENT : bandwidth;

        Stream<Path> paths = topologyService.getKShortestPaths(
                topologyService.currentTopology(),
                ingress.deviceId(), egress.deviceId(),
                new BandwidthLinkWeight(bandwidth));

        // Path service calculates from node to node, we're only interested in port to port
        Optional<OpticalConnectivityId> id =
                paths.filter(p -> p.src().equals(ingress) && p.dst().equals(egress))
                        .limit(maxPaths)
                        .map(p -> setupPath(p, bw, latency))
                        .filter(Objects::nonNull)
                        .findFirst();

        if (id.isPresent()) {
            log.info("Assigned OpticalConnectivityId: {}", id);
        } else {
            log.error("setupConnectivity({}, {}, {}, {}) failed.", ingress, egress, bandwidth, latency);
        }

        return id.orElse(null);
    }