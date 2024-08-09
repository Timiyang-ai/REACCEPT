public Set<String> suppressHostByProvider() {
        if (!object.has(SUPPRESS_HOST_BY_PROVIDER)) {
            return ImmutableSet.of();
        }

        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        ArrayNode arrayNode = (ArrayNode) object.path(SUPPRESS_HOST_BY_PROVIDER);
        for (JsonNode jsonNode : arrayNode) {
            String providerName = jsonNode.asText(null);
            if (providerName == null) {
                return null;
            }
            builder.add(providerName);
        }
        return builder.build();
    }