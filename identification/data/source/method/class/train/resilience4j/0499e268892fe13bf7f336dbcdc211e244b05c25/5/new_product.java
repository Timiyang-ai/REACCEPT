static <T> Consumer<T> decorateConsumer(RateLimiter rateLimiter, Consumer<T> consumer) {
		return decorateConsumer(rateLimiter, 1, consumer);
	}