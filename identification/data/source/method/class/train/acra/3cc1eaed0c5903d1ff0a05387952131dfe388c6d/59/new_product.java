public static void init(Application app) {
        ReportsCrashes reportsCrashes = mApplication.getClass().getAnnotation(ReportsCrashes.class);
        if (reportsCrashes == null) {
            log.e(LOG_TAG,
                    "ACRA#init called but no ReportsCrashes annotation on Application " + app.getPackageName());
            return;
        }
        init(app, new ACRAConfiguration(reportsCrashes));
    }