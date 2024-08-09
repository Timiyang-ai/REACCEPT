public <T> void execute(final SpiceRequest<T> request,
        final Object requestCacheKey, final long cacheExpiryDuration,
        final RequestListener<T> requestListener) {
        final CachedSpiceRequest<T> cachedSpiceRequest = new CachedSpiceRequest<T>(
            request, requestCacheKey, cacheExpiryDuration);
        execute(cachedSpiceRequest, requestListener);
    }