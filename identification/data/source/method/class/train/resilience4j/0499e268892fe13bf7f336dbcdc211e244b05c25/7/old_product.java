static <T> Consumer<T> decorateConsumer(RateLimiter rateLimiter, Consumer<T> consumer) {
        Consumer<T> decoratedConsumer = (T t) -> {
            waitForPermission(rateLimiter);
            consumer.accept(t);
        };
        return decoratedConsumer;
    }