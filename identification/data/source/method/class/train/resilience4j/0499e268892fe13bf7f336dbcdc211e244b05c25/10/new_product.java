static <T> CheckedFunction0<T> decorateCheckedSupplier(RateLimiter rateLimiter, CheckedFunction0<T> supplier) {
		return decorateCheckedSupplier(rateLimiter, 1, supplier);
	}