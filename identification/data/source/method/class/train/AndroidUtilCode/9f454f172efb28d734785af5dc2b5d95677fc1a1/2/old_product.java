public static <T> void executeByIoAtFixRate(final Task<T> task,
                                                long initialDelay,
                                                final long delay,
                                                final TimeUnit unit) {
        executeAtFixedRate(getPoolByTypeAndPriority(TYPE_CACHED), task, initialDelay, delay, unit);
    }