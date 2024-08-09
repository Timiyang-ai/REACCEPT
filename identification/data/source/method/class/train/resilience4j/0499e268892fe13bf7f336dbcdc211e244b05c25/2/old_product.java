static <T> Supplier<T> decorateSupplier(RateLimiter rateLimiter, Supplier<T> supplier) {
        Supplier<T> decoratedSupplier = () -> {
            waitForPermission(rateLimiter);
            T result = supplier.get();
            return result;
        };
        return decoratedSupplier;
    }