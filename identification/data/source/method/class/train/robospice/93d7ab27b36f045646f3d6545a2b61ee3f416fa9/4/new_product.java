public < T > void execute( SpiceRequest< T > request, RequestListener< T > requestListener ) {
        CachedSpiceRequest< T > cachedSpiceRequest = new CachedSpiceRequest< T >( request, null, DurationInMillis.ALWAYS );
        execute( cachedSpiceRequest, requestListener );
    }