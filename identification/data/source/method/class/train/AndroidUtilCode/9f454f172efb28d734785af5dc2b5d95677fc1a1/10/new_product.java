public static <T> void executeByCpuAtFixRate(final Task<T> task,
                                                 long initialDelay,
                                                 final long period,
                                                 final TimeUnit unit,
                                                 @IntRange(from = 1, to = 10) final int priority) {
        executeAtFixedRate(
                getPoolByTypeAndPriority(TYPE_CPU, priority), task, initialDelay, period, unit
        );
    }