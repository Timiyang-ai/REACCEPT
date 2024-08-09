public static <T> void executeByCpuAtFixRate(final Task<T> task,
                                                 long initialDelay,
                                                 final long delay,
                                                 final TimeUnit unit,
                                                 @IntRange(from = 1, to = 10) final int priority) {
        executeAtFixedRate(
                getPoolByTypeAndPriority(TYPE_CPU, priority), task, initialDelay, delay, unit
        );
    }