    private static CircuitBreakerStrategy strategy() {
        return (ctx, cause) -> CompletableFuture.completedFuture(cause == null);
    }