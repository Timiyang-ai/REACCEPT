public void dontNotifyRequestListenersForRequest(final CachedSpiceRequest<?> request, final Collection<RequestListener<?>> listRequestListener) {
        handlerResponse.removeCallbacksAndMessages(request.getRequestCacheKey());
        final Set<RequestListener<?>> setRequestListener = mapRequestToRequestListener.get(request);
        if (setRequestListener != null && listRequestListener != null) {
            Ln.d("Removing listeners of request : " + request.toString() + " : " + setRequestListener.size());
            setRequestListener.removeAll(listRequestListener);
        }
    }