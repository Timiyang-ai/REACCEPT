public static Future<Void> async(Runnable runnable) {
        if (autoCheckException) {
            checkExceptionWrapped();
        }
        Callable<Void> call = new ASyncFXCallable<>(runnable, true);
        return EXECUTOR_SERVICE.submit(call);
    }