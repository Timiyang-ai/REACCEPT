static <T> Supplier<T> decorateSupplier(RateLimiter rateLimiter, Supplier<T> supplier) {
		return decorateSupplier(rateLimiter, 1, supplier);
	}