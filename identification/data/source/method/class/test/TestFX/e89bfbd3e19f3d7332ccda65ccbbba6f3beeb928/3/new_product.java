public static <T> Future<T> async(Callable<T> callable) {
        if (autoCheckException) {
            checkExceptionWrapped();
        }
        ASyncFXCallable<T> call = new ASyncFXCallable<>(callable, true);
        executorService.submit((Runnable) call); // exception handling not guaranteed
        return call;
    }