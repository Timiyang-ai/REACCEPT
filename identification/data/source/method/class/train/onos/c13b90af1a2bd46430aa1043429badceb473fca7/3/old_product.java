public static ThreadFactory groupedThreads(String groupName, String pattern) {
        return new ThreadFactoryBuilder()
                .setThreadFactory(groupedThreadFactory(groupName))
                .setNameFormat(groupName.replace(GroupedThreadFactory.DELIMITER, "-") + "-" + pattern)
                .setUncaughtExceptionHandler((t, e) -> log.error("Uncaught exception on " + t.getName(), e))
                .build();
    }