public static SentryUncaughtExceptionHandler setup() {
        logger.debug("Configuring uncaught exception handler.");

        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (currentHandler != null) {
            logger.debug("default UncaughtExceptionHandler class='" + currentHandler.getClass().getName() + "'");
        }

        SentryUncaughtExceptionHandler handler = new SentryUncaughtExceptionHandler(currentHandler);
        Thread.setDefaultUncaughtExceptionHandler(handler);
        return handler;
    }