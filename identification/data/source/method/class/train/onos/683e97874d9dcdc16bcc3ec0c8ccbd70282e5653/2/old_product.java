public static ThreadFactory namedThreads(String pattern) {
        return new ThreadFactoryBuilder().setNameFormat(pattern).build();
    }