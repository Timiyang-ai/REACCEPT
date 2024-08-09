public static Future<Void> async(Runnable runnable) {
        Callable<Void> callable = Executors.callable(runnable, null);
        return async(callable);
    }