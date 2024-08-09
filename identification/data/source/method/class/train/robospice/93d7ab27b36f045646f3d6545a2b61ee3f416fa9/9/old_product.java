public <T> void execute(final SpiceRequest<T> request,
        final Object requestCacheKey, final long cacheDuration,
        final RequestListener<T> requestListener) {
        final CachedSpiceRequest<T> cachedSpiceRequest = new CachedSpiceRequest<T>(
            request, requestCacheKey, cacheDuration);
        execute(cachedSpiceRequest, requestListener);
    }