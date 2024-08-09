public static <T> void executeBySingleAtFixRate(final Task<T> task,
                                                    long initialDelay,
                                                    final long delay,
                                                    final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_SINGLE), task, initialDelay, delay, unit);
    }