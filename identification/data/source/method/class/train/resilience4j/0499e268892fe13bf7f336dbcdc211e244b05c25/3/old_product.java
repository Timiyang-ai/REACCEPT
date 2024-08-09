static Runnable decorateRunnable(RateLimiter rateLimiter, Runnable runnable) {
        Runnable decoratedRunnable = () -> {
            waitForPermission(rateLimiter);
            runnable.run();
        };
        return decoratedRunnable;
    }