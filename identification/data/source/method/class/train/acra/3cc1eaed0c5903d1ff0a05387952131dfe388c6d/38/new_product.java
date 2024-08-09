public static void init(@NonNull Application app, @NonNull CoreConfigurationBuilder builder, boolean checkReportsOnApplicationStart) {
        try {
            init(app, builder.build(), checkReportsOnApplicationStart);
        } catch (ACRAConfigurationException e) {
            log.w(LOG_TAG, "Configuration Error - ACRA not started : " + e.getMessage());
        }
    }