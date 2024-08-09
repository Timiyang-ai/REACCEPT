public static <T> void executeByCpuAtFixRate(final Task<T> task,
                                                 long initialDelay,
                                                 final long delay,
                                                 final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_CPU), task, initialDelay, delay, unit);
    }