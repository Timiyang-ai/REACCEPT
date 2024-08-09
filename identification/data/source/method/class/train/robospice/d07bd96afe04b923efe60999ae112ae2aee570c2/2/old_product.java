public void dontNotifyRequestListenersForRequest( CachedSpiceRequest< ? > request, Collection< RequestListener< ? >> listRequestListener ) {
        Set< RequestListener< ? >> setRequestListener = mapRequestToRequestListener.get( request );
        if ( setRequestListener != null && listRequestListener != null ) {
            setRequestListener.removeAll( listRequestListener );
        }
    }