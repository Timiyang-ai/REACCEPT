public Set<String> excludePorts() {
        if (!object.has(EXCLUDE_PORTS)) {
            return null;
        }

        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        ArrayNode arrayNode = (ArrayNode) object.path(EXCLUDE_PORTS);
        for (JsonNode jsonNode : arrayNode) {
            String portName = jsonNode.asText(null);
            if (portName == null) {
                return null;
            }
            builder.add(portName);
        }
        return builder.build();
    }