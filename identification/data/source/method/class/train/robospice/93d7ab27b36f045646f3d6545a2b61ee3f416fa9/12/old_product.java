public < T > void execute( SpiceRequest< T > request, Object requestCacheKey, long cacheDuration, RequestListener< T > requestListener ) {
        CachedSpiceRequest< T > cachedContentRequest = new CachedSpiceRequest< T >( request, requestCacheKey, cacheDuration );
        execute( cachedContentRequest, requestListener );
    }