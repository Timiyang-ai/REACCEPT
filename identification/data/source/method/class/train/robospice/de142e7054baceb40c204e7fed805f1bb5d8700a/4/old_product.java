public < T > void addListenerIfPending( Class< T > clazz, String requestCacheKey, long cacheDuration, RequestListener< T > requestListener ) {
        SpiceRequest< T > request = new SpiceRequest< T >( clazz ) {

            @Override
            public T loadDataFromNetwork() throws Exception {
                return null;
            }
        };
        CachedSpiceRequest< T > cachedContentRequest = new CachedSpiceRequest< T >( request, requestCacheKey, cacheDuration );
        cachedContentRequest.setProcessable( false );
        execute( cachedContentRequest, requestListener );
    }