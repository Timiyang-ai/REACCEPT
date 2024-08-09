public SegmentRoutingAppConfig setSuppressHost(Set<ConnectPoint> suppressHost) {
        if (suppressHost == null) {
            object.remove(SUPPRESS_HOST);
        } else {
            ArrayNode arrayNode = mapper.createArrayNode();
            suppressHost.forEach(connectPoint -> {
                arrayNode.add(connectPoint.deviceId() + "/" + connectPoint.port());
            });
            object.set(SUPPRESS_HOST, arrayNode);
        }
        return this;
    }