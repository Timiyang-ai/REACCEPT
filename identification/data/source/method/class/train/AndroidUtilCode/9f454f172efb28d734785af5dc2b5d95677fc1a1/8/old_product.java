public static <T> void executeBySingleAtFixRate(final Task<T> task,
                                                    final long delay,
                                                    final TimeUnit unit,
                                                    @IntRange(from = 1, to = 10) final int priority) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_SINGLE, priority), task, 0, delay, unit);
    }