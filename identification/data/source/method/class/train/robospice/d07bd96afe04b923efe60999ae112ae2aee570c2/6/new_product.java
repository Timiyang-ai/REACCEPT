public void dontNotifyRequestListenersForRequest( CachedSpiceRequest< ? > request, Collection< RequestListener< ? >> listRequestListener ) {
        handlerResponse.removeCallbacksAndMessages( request.getRequestCacheKey() );
        Set< RequestListener< ? >> setRequestListener = mapRequestToRequestListener.get( request );
        if ( setRequestListener != null && listRequestListener != null ) {
            setRequestListener.removeAll( listRequestListener );
        }
    }