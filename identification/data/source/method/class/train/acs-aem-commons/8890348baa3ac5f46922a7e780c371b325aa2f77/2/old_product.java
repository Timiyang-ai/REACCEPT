protected ValueMap getImageTransformersWithParams(final List<NamedImageTransformer> namedImageTransformers) {
        final ValueMap params = new ValueMapDecorator(new LinkedHashMap<String, Object>());

        for (final NamedImageTransformer namedImageTransformer : namedImageTransformers) {
            params.putAll(namedImageTransformer.getTransforms());
        }

        log.error("w params {}", params);

        return params;
    }