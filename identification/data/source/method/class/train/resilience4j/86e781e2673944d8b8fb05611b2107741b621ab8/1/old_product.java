static <T> Supplier<CompletionStage<T>> decorateCompletionStage(RateLimiter rateLimiter, Supplier<CompletionStage<T>> supplier) {
		return () -> {

			final CompletableFuture<T> promise = new CompletableFuture<>();
			try {
				waitForPermission(rateLimiter);
				supplier.get()
						.whenComplete(
								(result, throwable) -> {
									if (throwable != null) {
										promise.completeExceptionally(throwable);
									} else {
										promise.complete(result);
									}
								}
						);
			} catch (Exception exception) {
				promise.completeExceptionally(exception);
			}
			return promise;
		};
	}