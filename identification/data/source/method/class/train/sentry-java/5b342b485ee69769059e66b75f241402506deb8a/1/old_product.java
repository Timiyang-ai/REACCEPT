public static void setup() {
        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (currentHandler != null) {
            logger.debug("default UncaughtExceptionHandler class='" + currentHandler.getClass().getName() + "'");
        }

        // don't double register
        if (!(currentHandler instanceof SentryUncaughtExceptionHandler)) {
            // register as default exception handler
            Thread.setDefaultUncaughtExceptionHandler(
                new SentryUncaughtExceptionHandler(currentHandler));
        }
    }