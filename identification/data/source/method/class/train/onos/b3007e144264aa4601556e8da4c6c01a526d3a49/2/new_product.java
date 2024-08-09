public SegmentRoutingAppConfig setSuppressHostByPort(Set<ConnectPoint> connectPoints) {
        if (connectPoints == null) {
            object.remove(SUPPRESS_HOST_BY_PORT);
        } else {
            ArrayNode arrayNode = mapper.createArrayNode();
            connectPoints.forEach(connectPoint -> {
                arrayNode.add(connectPoint.deviceId() + "/" + connectPoint.port());
            });
            object.set(SUPPRESS_HOST_BY_PORT, arrayNode);
        }
        return this;
    }