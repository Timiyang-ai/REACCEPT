public static <T> void executeByCpuAtFixRate(final Task<T> task,
                                                 final long period,
                                                 final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_CPU), task, 0, period, unit);
    }