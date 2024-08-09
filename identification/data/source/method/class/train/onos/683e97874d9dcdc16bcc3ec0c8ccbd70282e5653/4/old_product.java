public static ThreadFactory namedThreads(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                // FIXME remove UncaughtExceptionHandler before release
                .setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        TOOLS_LOG.error("Uncaught exception on {}", t.getName(), e);
                    }
                }).build();
    }