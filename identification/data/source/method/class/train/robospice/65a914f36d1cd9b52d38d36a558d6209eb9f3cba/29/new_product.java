public void addRequest(final CachedSpiceRequest<?> request, final Set<RequestListener<?>> listRequestListener) {
        Ln.d("Adding request to queue " + hashCode() + ": " + request + " size is " + mapRequestToRequestListener.size());

        if (request.isCancelled()) {
            synchronized (mapRequestToRequestListener) {
                for (final CachedSpiceRequest<?> cachedSpiceRequest : mapRequestToRequestListener.keySet()) {
                    if (cachedSpiceRequest.equals(request)) {
                        cachedSpiceRequest.cancel();
                        requestProgressManager.notifyListenersOfRequestCancellation(request);
                        return;
                    }
                }
            }
        }

        boolean aggregated = false;
        Set<RequestListener<?>> listRequestListenerForThisRequest = mapRequestToRequestListener.get(request);

        if (listRequestListenerForThisRequest == null) {
            if (request.isProcessable()) {
                Ln.d("Adding entry for type %s and cacheKey %s.", request.getResultType(), request.getRequestCacheKey());
                listRequestListenerForThisRequest = Collections.synchronizedSet(new HashSet<RequestListener<?>>());
                this.mapRequestToRequestListener.put(request, listRequestListenerForThisRequest);
            }
        } else {
            Ln.d("Request for type %s and cacheKey %s already exists.", request.getResultType(), request.getRequestCacheKey());
            aggregated = true;
        }

        if (listRequestListener != null && listRequestListenerForThisRequest != null) {
            listRequestListenerForThisRequest.addAll(listRequestListener);
        }

        if (aggregated) {
            requestProgressManager.notifyListenersOfRequestAggregated(request, listRequestListener);
            return;
        }

        if (request.isProcessable()) {
            requestProgressManager.notifyListenersOfRequestAdded(request, listRequestListener);
        } else {
            if (listRequestListenerForThisRequest == null) {
                requestProgressManager.notifyListenersOfRequestNotFound(request, listRequestListener);
            }
            requestProgressManager.notifyOfRequestProcessed(request, listRequestListener);
            //we have to return if request is not processable.
            //fix bug https://github.com/octo-online/robospice/issues/215
            return;
        }

        final RequestCancellationListener requestCancellationListener = new RequestCancellationListener() {

            @Override
            public void onRequestCancelled() {
                requestProgressManager.notifyListenersOfRequestCancellation(request);
                mapRequestToRequestListener.remove(request);
            }
        };
        request.setRequestCancellationListener(requestCancellationListener);

        if (request.isCancelled()) {
            requestProgressManager.notifyListenersOfRequestCancellation(request);
            mapRequestToRequestListener.remove(request);
            return;
        } else {
            requestRunner.executeRequest(request);
        }
    }