public static <T> void executeBySingle(final Task<T> task) {
        getPoolByTypeAndPriority(TYPE_SINGLE).execute(task);
    }