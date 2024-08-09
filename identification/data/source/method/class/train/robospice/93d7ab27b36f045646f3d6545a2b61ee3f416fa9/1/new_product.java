public <T> void execute(final CachedSpiceRequest<T> cachedSpiceRequest,
                        final RequestListener<T> requestListener) {
                addRequestListenerToListOfRequestListeners(cachedSpiceRequest,
                                requestListener);
                this.requestQueue.add(cachedSpiceRequest);
        }