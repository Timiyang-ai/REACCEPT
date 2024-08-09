public < T > void execute( SpiceRequest< T > request, RequestListener< T > requestListener ) {
        CachedSpiceRequest< T > cachedContentRequest = new CachedSpiceRequest< T >( request, null, DurationInMillis.ALWAYS );
        execute( cachedContentRequest, requestListener );
    }