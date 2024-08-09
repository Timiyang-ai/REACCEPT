static <T> Supplier<CompletionStage<T>> decorateCompletionStage(RateLimiter rateLimiter, Supplier<CompletionStage<T>> supplier) {
		return decorateCompletionStage(rateLimiter, 1, supplier);
	}