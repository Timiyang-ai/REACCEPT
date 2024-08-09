public static <T> void executeByIoAtFixRate(final Task<T> task,
                                                long initialDelay,
                                                final long period,
                                                final TimeUnit unit,
                                                @IntRange(from = 1, to = 10) final int priority) {
        executeAtFixedRate(
                getPoolByTypeAndPriority(TYPE_IO, priority), task, initialDelay, period, unit
        );
    }