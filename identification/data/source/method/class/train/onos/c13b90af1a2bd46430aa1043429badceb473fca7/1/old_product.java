public static ThreadFactory groupedThreads(String groupName, String pattern) {
        return new ThreadFactoryBuilder()
                .setThreadFactory(groupedThreadFactory(groupName))
                .setNameFormat(pattern)
                        // FIXME remove UncaughtExceptionHandler before release
                .setUncaughtExceptionHandler((t, e) -> log.error("Uncaught exception on {}", t.getName(), e)).build();
    }