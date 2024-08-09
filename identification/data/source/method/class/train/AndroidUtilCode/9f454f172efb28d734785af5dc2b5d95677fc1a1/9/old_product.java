public static <T> void executeByIo(final Task<T> task) {
        execute(getPoolByTypeAndPriority(TYPE_CACHED), task);
    }