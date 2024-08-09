static <T> Supplier<T> decorateSupplier(RateLimiter rateLimiter, Supplier<T> supplier) {
		return () -> {
			waitForPermission(rateLimiter);
			return supplier.get();
		};
	}