public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) final int size,
                                                   final Task<T> task,
                                                   long initialDelay,
                                                   final long period,
                                                   final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, initialDelay, period, unit);
    }