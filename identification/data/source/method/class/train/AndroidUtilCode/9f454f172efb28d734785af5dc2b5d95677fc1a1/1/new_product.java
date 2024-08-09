public static <T> void executeBySingleAtFixRate(final Task<T> task,
                                                    long initialDelay,
                                                    final long period,
                                                    final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_SINGLE), task, initialDelay, period, unit);
    }