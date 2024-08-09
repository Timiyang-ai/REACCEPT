public static <T> void executeByIo(final Task<T> task,
                                       @IntRange(from = 1, to = 10) final int priority) {
        execute(getPoolByTypeAndPriority(TYPE_IO, priority), task);
    }