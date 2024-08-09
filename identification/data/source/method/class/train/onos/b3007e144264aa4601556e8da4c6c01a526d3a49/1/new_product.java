public SegmentRoutingAppConfig setSuppressHostByProvider(Set<String> providers) {
        if (providers == null) {
            object.remove(SUPPRESS_HOST_BY_PROVIDER);
        } else {
            ArrayNode arrayNode = mapper.createArrayNode();
            providers.forEach(arrayNode::add);
            object.set(SUPPRESS_HOST_BY_PROVIDER, arrayNode);
        }
        return this;
    }