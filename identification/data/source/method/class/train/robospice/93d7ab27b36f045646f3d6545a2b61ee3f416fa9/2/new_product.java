public < T > void cancel( Class< T > clazz, String requestCacheKey ) {
        SpiceRequest< T > request = new SpiceRequest< T >( clazz ) {

            @Override
            public T loadDataFromNetwork() throws Exception {
                return null;
            }
        };
        CachedSpiceRequest< T > cachedSpiceRequest = new CachedSpiceRequest< T >( request, requestCacheKey, DurationInMillis.NEVER );
        cachedSpiceRequest.setProcessable( false );
        cachedSpiceRequest.cancel();
        execute( cachedSpiceRequest, null );
    }