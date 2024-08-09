private MathTransformProvider getProvider(final String method) throws NoSuchIdentifierException {
        /*
         * Copies the 'lastProvider' reference in order to avoid synchronization. This is safe
         * because copy of object references are atomic operations.  Note that this is not the
         * deprecated "double check" idiom since we are not creating new objects, but checking
         * for existing ones.
         */
        MathTransformProvider provider = lastProvider;
        if (provider!=null && provider.nameMatches(method)) {
            return provider;
        }

        provider = registry.getFactories(MathTransformProvider.class, null, HINTS)
                .filter(prov -> prov.nameMatches(method))
                .findAny()
                .orElseThrow(() -> new NoSuchIdentifierException(Errors
                        .format(ErrorKeys.NO_TRANSFORM_FOR_CLASSIFICATION_$1, method), method));

        return lastProvider = provider;
    }