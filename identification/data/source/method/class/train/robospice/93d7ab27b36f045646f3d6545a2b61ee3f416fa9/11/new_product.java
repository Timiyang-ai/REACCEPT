public <T> void execute(final CachedSpiceRequest<T> cachedSpiceRequest, final RequestListener<T> requestListener) {
        addRequestListenerToListOfRequestListeners(cachedSpiceRequest, requestListener);
        Ln.d("adding request to request queue");
        this.requestQueue.add(cachedSpiceRequest);
    }