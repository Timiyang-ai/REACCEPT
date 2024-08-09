static Runnable decorateRunnable(RateLimiter rateLimiter, Runnable runnable) {
		return decorateRunnable(rateLimiter, 1, runnable);
	}