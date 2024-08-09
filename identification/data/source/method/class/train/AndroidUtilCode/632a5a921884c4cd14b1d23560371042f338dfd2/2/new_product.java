public static <T> void executeBySingle(final Task<T> task) {
        getScheduledByTask(task).execute(new Runnable() {
            @Override
            public void run() {
                getPoolByTypeAndPriority(TYPE_SINGLE).execute(task);
            }
        });
//        execute(getPoolByTypeAndPriority(TYPE_SINGLE), task);
    }