public < T > void addListenerIfPending( Class< T > clazz, String requestCacheKey, RequestListener< T > requestListener ) {
        SpiceRequest< T > request = new SpiceRequest< T >( clazz ) {

            @Override
            public T loadDataFromNetwork() throws Exception {
                return null;
            }
        };
        CachedSpiceRequest< T > cachedSpiceRequest = new CachedSpiceRequest< T >( request, requestCacheKey, DurationInMillis.NEVER );
        cachedSpiceRequest.setProcessable( false );
        execute( cachedSpiceRequest, requestListener );
    }