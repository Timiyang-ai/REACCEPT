public static <T> Future<T> async(Callable<T> callable, boolean throwExceptions) {
        if (autoCheckException) {
            checkExceptionWrapped();
        }
        Callable<T> call = new ASyncFXCallable<>(callable, throwExceptions);
        return EXECUTOR_SERVICE.submit(call); // exception handling not guaranteed
    }