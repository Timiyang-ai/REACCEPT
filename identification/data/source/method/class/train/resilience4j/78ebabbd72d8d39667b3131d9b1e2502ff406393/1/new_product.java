static <T> Supplier<Try<T>> decorateTrySupplier(RateLimiter rateLimiter, Supplier<Try<T>> supplier) {
		return decorateTrySupplier(rateLimiter, 1, supplier);
	}