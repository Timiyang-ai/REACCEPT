public static Future<Void> async(Runnable runnable, boolean throwExceptions) {
        if (autoCheckException) {
            checkExceptionWrapped();
        }
        Callable<Void> call = new ASyncFXCallable<>(runnable, throwExceptions);
        return EXECUTOR_SERVICE.submit(call);
    }