public void addRequest(final CachedSpiceRequest<?> request,
                        final Set<RequestListener<?>> listRequestListener) {
                Ln.d("Adding request to queue " + hashCode() + ": " + request
                                + " size is "
                                + mapRequestToRequestListener.size());

                if (request.isCancelled()) {
                        for (final CachedSpiceRequest<?> cachedSpiceRequest : mapRequestToRequestListener
                                        .keySet()) {
                                if (cachedSpiceRequest.equals(request)) {
                                        cachedSpiceRequest.cancel();
                                        return;
                                }
                        }
                }

                boolean aggregated = false;
                if (listRequestListener != null) {
                        Set<RequestListener<?>> listRequestListenerForThisRequest = mapRequestToRequestListener
                                        .get(request);

                        if (listRequestListenerForThisRequest == null) {
                                listRequestListenerForThisRequest = new HashSet<RequestListener<?>>();
                                this.mapRequestToRequestListener
                                                .put(request,
                                                                listRequestListenerForThisRequest);
                        } else {
                                Ln.d(String.format(
                                                "Request for type %s and cacheKey %s already exists.",
                                                request.getResultType(),
                                                request.getRequestCacheKey()));
                                aggregated = true;
                        }

                        listRequestListenerForThisRequest
                                        .addAll(listRequestListener);
                        if (request.isProcessable()) {
                                notifyListenersOfRequestProgress(request,
                                                listRequestListener,
                                                request.getProgress());
                        }
                }

                if (aggregated) {
                        return;
                }

                final RequestCancellationListener requestCancellationListener = new RequestCancellationListener() {

                        @Override
                        public void onRequestCancelled() {
                                mapRequestToRequestListener.remove(request);
                                notifyListenersOfRequestCancellation(request,
                                                listRequestListener);
                        }
                };
                request.setRequestCancellationListener(requestCancellationListener);

                if (request.isCancelled()) {
                        mapRequestToRequestListener.remove(request);
                        notifyListenersOfRequestCancellation(request,
                                        listRequestListener);
                        return;
                } else {

                        final Future<?> future = executorService
                                        .submit(new Runnable() {
                                                @Override
                                                public void run() {
                                                        try {
                                                                processRequest(request);
                                                        } catch (final Throwable t) {
                                                                Ln.d(t,
                                                                                "An unexpected error occured when processsing request %s",
                                                                                request.toString());

                                                        }
                                                }
                                        });
                        request.setFuture(future);
                }
        }