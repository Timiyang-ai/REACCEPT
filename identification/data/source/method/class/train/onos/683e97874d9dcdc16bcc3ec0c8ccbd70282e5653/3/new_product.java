public static ThreadFactory namedThreads(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                .setUncaughtExceptionHandler((t, e) -> log.error("Uncaught exception on " + t.getName(), e))
                .build();
    }