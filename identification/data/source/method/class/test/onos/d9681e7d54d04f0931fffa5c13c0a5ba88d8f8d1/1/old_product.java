public SegmentRoutingAppConfig setExcludePorts(Set<String> excludePorts) {
        if (excludePorts == null) {
            object.remove(EXCLUDE_PORTS);
        } else {
            ArrayNode arrayNode = mapper.createArrayNode();
            excludePorts.forEach(portName -> {
                arrayNode.add(portName);
            });
            object.set(EXCLUDE_PORTS, arrayNode);
        }
        return this;
    }