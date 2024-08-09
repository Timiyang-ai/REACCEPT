static CheckedRunnable decorateCheckedRunnable(RateLimiter rateLimiter, CheckedRunnable runnable) {

		return decorateCheckedRunnable(rateLimiter, 1, runnable);
	}