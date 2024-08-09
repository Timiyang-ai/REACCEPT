public static <T> void executeBySingle(final Task<T> task) {
        execute(getPoolByTypeAndPriority(TYPE_SINGLE), task);
    }