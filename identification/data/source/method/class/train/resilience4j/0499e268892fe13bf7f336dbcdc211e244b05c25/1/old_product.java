static CheckedRunnable decorateCheckedRunnable(RateLimiter rateLimiter, CheckedRunnable runnable) {

		return () -> {
			waitForPermission(rateLimiter);
			runnable.run();
		};
	}