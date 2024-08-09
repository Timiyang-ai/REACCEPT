static <T> Try.CheckedSupplier<T> decorateCheckedSupplier(RateLimiter rateLimiter, Try.CheckedSupplier<T> supplier) {
        Try.CheckedSupplier<T> decoratedSupplier = () -> {
            waitForPermission(rateLimiter);
            T result = supplier.get();
            return result;
        };
        return decoratedSupplier;
    }