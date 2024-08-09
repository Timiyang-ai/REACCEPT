public void addRequest( final CachedSpiceRequest< ? > request, Set< RequestListener< ? >> listRequestListener ) {
        Log.d( LOG_CAT, "Adding request to queue " + hashCode() + ": " + request + " size is " + mapRequestToRequestListener.size() );

        if ( request.isCancelled() ) {
            for ( CachedSpiceRequest< ? > cachedContentRequest : mapRequestToRequestListener.keySet() ) {
                if ( cachedContentRequest.equals( request ) ) {
                    cachedContentRequest.cancel();
                    return;
                }
            }
        }

        boolean aggregated = false;
        if ( listRequestListener != null ) {
            Set< RequestListener< ? >> listRequestListenerForThisRequest = mapRequestToRequestListener.get( request );

            if ( listRequestListenerForThisRequest == null ) {
                listRequestListenerForThisRequest = new HashSet< RequestListener< ? >>();
                this.mapRequestToRequestListener.put( request, listRequestListenerForThisRequest );
            } else {
                Log.d( LOG_CAT, String.format( "Request for type %s and cacheKey %s already exists.", request.getResultType(), request.getRequestCacheKey() ) );
                aggregated = true;
            }

            listRequestListenerForThisRequest.addAll( listRequestListener );
            notifyListenersOfRequestProgress( listRequestListener, request.getProgress() );
        }

        if ( aggregated ) {
            return;
        }

        Future< ? > future = executorService.submit( new Runnable() {
            public void run() {
                processRequest( request );
            }
        } );
        request.setFuture( future );
    }