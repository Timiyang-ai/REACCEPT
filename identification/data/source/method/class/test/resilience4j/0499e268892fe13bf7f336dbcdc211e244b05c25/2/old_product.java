static Try.CheckedRunnable decorateCheckedRunnable(RateLimiter rateLimiter, Try.CheckedRunnable runnable) {

        Try.CheckedRunnable decoratedRunnable = () -> {
            waitForPermission(rateLimiter);
            runnable.run();
        };
        return decoratedRunnable;
    }