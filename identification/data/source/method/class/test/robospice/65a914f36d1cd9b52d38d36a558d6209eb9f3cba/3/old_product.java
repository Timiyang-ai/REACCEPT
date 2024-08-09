public void addRequest(final CachedSpiceRequest<?> request, final Set<RequestListener<?>> listRequestListener) {
        Ln.d("Adding request to queue " + hashCode() + ": " + request + " size is " + mapRequestToRequestListener.size());

        if (request.isCancelled()) {
            synchronized (mapRequestToRequestListener) {
                for (final CachedSpiceRequest<?> cachedSpiceRequest : mapRequestToRequestListener.keySet()) {
                    if (cachedSpiceRequest.equals(request)) {
                        cachedSpiceRequest.cancel();
                        return;
                    }
                }
            }
        }

        boolean aggregated = false;
        if (listRequestListener != null) {
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

            if (listRequestListenerForThisRequest != null) {
                listRequestListenerForThisRequest.addAll(listRequestListener);
            }
            if (request.isProcessable()) {
                notifyListenersOfRequestProgress(request, listRequestListener, request.getProgress());
            }
        }

        if (aggregated) {
            return;
        }

        final RequestCancellationListener requestCancellationListener = new RequestCancellationListener() {

            @Override
            public void onRequestCancelled() {
                mapRequestToRequestListener.remove(request);
                notifyListenersOfRequestCancellation(request, listRequestListener);
            }
        };
        request.setRequestCancellationListener(requestCancellationListener);

        if (request.isCancelled()) {
            mapRequestToRequestListener.remove(request);
            notifyListenersOfRequestCancellation(request, listRequestListener);
            return;
        } else if (!request.isProcessable()) {
            notifyOfRequestProcessed(request);
            return;
        } else {
            planRequestExecution(request);
        }
    }