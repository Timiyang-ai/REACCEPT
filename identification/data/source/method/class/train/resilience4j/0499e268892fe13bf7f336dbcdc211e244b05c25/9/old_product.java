static Try.CheckedRunnable decorateCheckedRunnable(RateLimiter rateLimiter, Try.CheckedRunnable runnable) {

        return () -> {
            waitForPermission(rateLimiter);
            runnable.run();
        };
    }