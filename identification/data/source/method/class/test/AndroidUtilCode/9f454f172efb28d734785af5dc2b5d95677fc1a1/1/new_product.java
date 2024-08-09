public static <T> void executeByCpuAtFixRate(final Task<T> task,
                                                 long initialDelay,
                                                 final long period,
                                                 final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_CPU), task, initialDelay, period, unit);
    }