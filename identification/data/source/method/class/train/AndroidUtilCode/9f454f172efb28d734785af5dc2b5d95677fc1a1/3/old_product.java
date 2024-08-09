public static <T> void executeBySingleAtFixRate(final Task<T> task,
                                                    final long delay,
                                                    final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_SINGLE), task, 0, delay, unit);
    }