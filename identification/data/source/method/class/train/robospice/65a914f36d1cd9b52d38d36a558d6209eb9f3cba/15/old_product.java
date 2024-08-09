public void addRequest(final CachedContentRequest<?> request, Set<RequestListener<?>> listRequestListener) {
		Log.d(LOG_CAT, "Adding request to queue : " + request);

		if (listRequestListener != null) {
			Set<RequestListener<?>> listRequestListenerForThisRequest = mapRequestToRequestListener.get(request);

			if (listRequestListenerForThisRequest == null) {
				listRequestListenerForThisRequest = new HashSet<RequestListener<?>>();
				this.mapRequestToRequestListener.put(request, listRequestListenerForThisRequest);
			}

			listRequestListenerForThisRequest.addAll(listRequestListener);
		}

		Future<?> future = executorService.submit(new Runnable() {
			public void run() {
				processRequest(request);
			}
		});
		request.setFuture(future);
	}