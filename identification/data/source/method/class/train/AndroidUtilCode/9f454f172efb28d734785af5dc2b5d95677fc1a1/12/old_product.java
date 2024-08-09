public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) final int size,
                                                   final Task<T> task,
                                                   final long delay,
                                                   final TimeUnit unit,
                                                   @IntRange(from = 1, to = 10) final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(size, priority), task, 0, delay, unit);
    }