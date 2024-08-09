public <T> void cancel(final Class<T> clazz, final Object requestCacheKey) {
        final SpiceRequest<T> request = new SpiceRequest<T>(clazz) {

            @Override
            public T loadDataFromNetwork() throws Exception {
                return null;
            }
        };
        final CachedSpiceRequest<T> cachedSpiceRequest = new CachedSpiceRequest<T>(request, requestCacheKey, DurationInMillis.ALWAYS_EXPIRED);
        cachedSpiceRequest.setProcessable(false);
        cachedSpiceRequest.cancel();
        execute(cachedSpiceRequest, null);
    }