public static <T> void executeByIoAtFixRate(final Task<T> task,
                                                final long delay,
                                                final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_CACHED), task, 0, delay, unit);
    }