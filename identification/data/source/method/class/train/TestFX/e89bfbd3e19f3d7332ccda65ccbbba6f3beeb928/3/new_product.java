public static <T> Future<T> async(Callable<T> callable, boolean throwExceptions) {
        if (autoCheckException) {
            checkExceptionWrapped();
        }
        Callable<T> call = new ASyncFXCallable<T>(callable, throwExceptions);
        return executorService.submit(call); //exception handling not guaranteed
    }