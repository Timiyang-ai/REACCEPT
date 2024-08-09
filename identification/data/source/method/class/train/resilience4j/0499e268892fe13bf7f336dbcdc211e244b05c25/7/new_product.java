static <T> Consumer<T> decorateConsumer(RateLimiter rateLimiter, Consumer<T> consumer) {
        return (T t) -> {
            waitForPermission(rateLimiter);
            consumer.accept(t);
        };
    }