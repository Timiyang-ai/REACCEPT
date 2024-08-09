static Runnable decorateRunnable(RateLimiter rateLimiter, Runnable runnable) {
		return () -> {
			waitForPermission(rateLimiter);
			runnable.run();
		};
	}