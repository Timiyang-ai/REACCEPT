public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) final int size,
                                                   final Task<T> task,
                                                   long initialDelay,
                                                   final long delay,
                                                   final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, initialDelay, delay, unit);
    }