public < T > void addListenerIfPending( Class< T > clazz, String requestCacheKey, long cacheDuration, RequestListener< T > requestListener ) {
        SpiceRequest< T > request = new SpiceRequest< T >( clazz ) {

            @Override
            public T loadDataFromNetwork() throws Exception {
                return null;
            }
        };
        CachedSpiceRequest< T > cachedSpiceRequest = new CachedSpiceRequest< T >( request, requestCacheKey, cacheDuration );
        cachedSpiceRequest.setProcessable( false );
        execute( cachedSpiceRequest, requestListener );
    }