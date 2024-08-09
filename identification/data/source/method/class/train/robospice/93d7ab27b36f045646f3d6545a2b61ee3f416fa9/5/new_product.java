public < T > void execute( CachedSpiceRequest< T > cachedSpiceRequest, RequestListener< T > requestListener ) {
        addRequestListenerToListOfRequestListeners( cachedSpiceRequest, requestListener );
        this.requestQueue.add( cachedSpiceRequest );
    }