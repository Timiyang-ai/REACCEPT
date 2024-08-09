static <T> Try.CheckedSupplier<T> decorateCheckedSupplier(RateLimiter rateLimiter, Try.CheckedSupplier<T> supplier) {
        return () -> {
            waitForPermission(rateLimiter);
            return supplier.get();
        };
    }