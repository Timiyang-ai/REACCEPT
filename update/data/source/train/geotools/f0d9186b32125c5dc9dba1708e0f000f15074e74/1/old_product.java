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
        final Iterator<MathTransformProvider> providers =
                registry.getServiceProviders(MathTransformProvider.class, null, HINTS);
        while (providers.hasNext()) {
            provider = providers.next();
            if (provider.nameMatches(method)) {
                return lastProvider = provider;
            }
        }
        throw new NoSuchIdentifierException(Errors.format(
                  ErrorKeys.NO_TRANSFORM_FOR_CLASSIFICATION_$1, method), method);
    }