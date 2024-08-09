public void dontNotifyRequestListenersForRequest( CachedSpiceRequest< ? > request, Collection< RequestListener< ? >> listRequestListener ) {
        handlerResponse.removeCallbacksAndMessages( request.getRequestCacheKey() );
        // Ouh that hurts, Release 1.3.0 fails, it doesn't have any request in map any more
        // TODO
        Set< RequestListener< ? >> setRequestListener = mapRequestToRequestListener.get( request );
        if ( setRequestListener != null && listRequestListener != null ) {
            Ln.d( "Removing listeners of request : " + request.toString() + " : " + setRequestListener.size() );
            setRequestListener.removeAll( listRequestListener );
        }
    }