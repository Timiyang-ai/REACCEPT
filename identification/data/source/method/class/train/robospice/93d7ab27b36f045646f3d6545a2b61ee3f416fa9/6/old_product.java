public <T> void execute(final SpiceRequest<T> request,
        final RequestListener<T> requestListener) {
        final CachedSpiceRequest<T> cachedSpiceRequest = new CachedSpiceRequest<T>(
            request, null, DurationInMillis.ALWAYS);
        execute(cachedSpiceRequest, requestListener);
    }