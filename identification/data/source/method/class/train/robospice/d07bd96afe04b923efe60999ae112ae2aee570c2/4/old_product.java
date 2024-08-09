public void dontNotifyRequestListenersForRequest(ContentRequest<?> request, Collection<RequestListener<?>> listRequestListener) {
		for (CachedContentRequest<?> cachedContentRequest : mapRequestToRequestListener.keySet()) {
			if (cachedContentRequest.getContentRequest().equals(request)) {
				Set<RequestListener<?>> setRequestListener = mapRequestToRequestListener.get(cachedContentRequest);
				if (setRequestListener != null && listRequestListener != null) {
					setRequestListener.removeAll(listRequestListener);
				}
				break;
			}
		}
	}