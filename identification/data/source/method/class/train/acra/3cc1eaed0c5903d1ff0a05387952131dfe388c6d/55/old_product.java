public static void init(@NonNull Application app) {
        final AcraCore reportsCrashes = app.getClass().getAnnotation(AcraCore.class);
        if (reportsCrashes == null) {
            log.e(LOG_TAG, "ACRA#init(Application) called but no ReportsCrashes annotation on Application " + app.getPackageName());
            return;
        }
        init(app, new CoreConfigurationBuilder(app));
    }