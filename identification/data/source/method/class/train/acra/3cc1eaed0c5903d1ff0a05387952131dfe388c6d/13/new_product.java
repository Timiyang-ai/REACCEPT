public static void init(@NonNull Application app) {
        final ReportsCrashes reportsCrashes = app.getClass().getAnnotation(ReportsCrashes.class);
        if (reportsCrashes == null) {
            log.e(LOG_TAG, "ACRA#init(Application) called but no ReportsCrashes annotation on Application " + app.getPackageName());
            return;
        }
        try {
            init(app, new ConfigurationBuilder(app).build());
        } catch (ACRAConfigurationException e) {
            log.w(LOG_TAG, "Configuration Error - ACRA not started : " + e.getMessage());
        }
    }