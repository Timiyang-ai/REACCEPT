public void cancelAllRequests() {
        try {
            lockSendRequestsToService.lock();
            // cancel each request that to be sent to service, and keep
            // listening for
            // cancellation.
            synchronized ( mapRequestToLaunchToRequestListener ) {
                for ( CachedSpiceRequest< ? > cachedContentRequest : mapRequestToLaunchToRequestListener.keySet() ) {
                    cachedContentRequest.cancel();
                }
            }

            // cancel each request that has been sent to service, and keep
            // listening for cancellation.
            // we must duplicate the list as each call to cancel will, by a listener of request processing
            // remove the request from our list.
            synchronized ( mapPendingRequestToRequestListener ) {
                List< CachedSpiceRequest< ? >> listDuplicate = new ArrayList< CachedSpiceRequest< ? > >( mapPendingRequestToRequestListener.keySet() );
                for ( CachedSpiceRequest< ? > cachedContentRequest : listDuplicate ) {
                    cachedContentRequest.cancel();
                }
            }
        } finally {
            lockSendRequestsToService.unlock();
        }
    }