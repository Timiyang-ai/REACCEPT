public static ThreadFactory namedThreads(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                        // FIXME remove UncaughtExceptionHandler before release
                .setUncaughtExceptionHandler((t, e) -> log.error("Uncaught exception on {}", t.getName(), e)).build();
    }