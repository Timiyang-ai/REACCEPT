public <T> void execute(final CachedSpiceRequest<T> cachedSpiceRequest, final RequestListener<T> requestListener) {
        addRequestListenerToListOfRequestListeners(cachedSpiceRequest, requestListener);
        System.out.println("adding request to request queue");
        this.requestQueue.add(cachedSpiceRequest);
    }