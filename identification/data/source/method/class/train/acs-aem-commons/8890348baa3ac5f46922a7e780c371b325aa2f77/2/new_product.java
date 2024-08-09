protected final ValueMap getImageTransformersWithParams(
            final List<NamedImageTransformer> selectedNamedImageTransformers) {
        final ValueMap params = new ValueMapDecorator(new LinkedHashMap<String, Object>());

        for (final NamedImageTransformer namedImageTransformer : selectedNamedImageTransformers) {
            params.putAll(namedImageTransformer.getTransforms());
        }

        log.error("w params {}", params);

        return params;
    }