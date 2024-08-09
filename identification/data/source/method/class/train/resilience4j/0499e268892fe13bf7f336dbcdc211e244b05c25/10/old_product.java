static <T> CheckedFunction0<T> decorateCheckedSupplier(RateLimiter rateLimiter, CheckedFunction0<T> supplier) {
		return () -> {
			waitForPermission(rateLimiter);
			return supplier.apply();
		};
	}