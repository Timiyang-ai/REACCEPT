public static <T> Future<T> async(Callable<T> callable) {
        SettableFuture<T> future = SettableFuture.create();
        runOnThread(() -> callCallableAndSetFuture(callable, future), executorService);
        return future;
    }