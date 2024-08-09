public static void init(Application app) {
        final ReportsCrashes reportsCrashes = app.getClass().getAnnotation(ReportsCrashes.class);
        if (reportsCrashes == null) {
            log.e(LOG_TAG, "ACRA#init called but no ReportsCrashes annotation on Application " + app.getPackageName());
            return;
        }
        init(app, new ACRAConfiguration(reportsCrashes));
    }