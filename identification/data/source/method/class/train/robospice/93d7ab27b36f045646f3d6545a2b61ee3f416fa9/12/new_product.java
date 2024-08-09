public < T > void execute( SpiceRequest< T > request, Object requestCacheKey, long cacheDuration, RequestListener< T > requestListener ) {
        CachedSpiceRequest< T > cachedSpiceRequest = new CachedSpiceRequest< T >( request, requestCacheKey, cacheDuration );
        execute( cachedSpiceRequest, requestListener );
    }