public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) final int size,
                                                   final Task<T> task,
                                                   final long delay,
                                                   final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(size), task, 0, delay, unit);
    }