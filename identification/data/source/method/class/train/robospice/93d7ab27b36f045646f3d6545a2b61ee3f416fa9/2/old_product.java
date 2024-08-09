public < T > void cancel( Class< T > clazz, String requestCacheKey ) {
        SpiceRequest< T > request = new SpiceRequest< T >( clazz ) {

            @Override
            public T loadDataFromNetwork() throws Exception {
                return null;
            }
        };
        CachedSpiceRequest< T > cachedContentRequest = new CachedSpiceRequest< T >( request, requestCacheKey, DurationInMillis.NEVER );
        cachedContentRequest.setProcessable( false );
        cachedContentRequest.cancel();
        execute( cachedContentRequest, null );
    }