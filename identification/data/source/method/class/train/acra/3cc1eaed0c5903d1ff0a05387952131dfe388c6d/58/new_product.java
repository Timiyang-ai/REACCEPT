public static void init(@NonNull Application app, @NonNull CoreConfiguration config, boolean checkReportsOnApplicationStart) {

        final boolean senderServiceProcess = isACRASenderServiceProcess();
        if (senderServiceProcess) {
            if (ACRA.DEV_LOGGING)
                log.d(LOG_TAG, "Not initialising ACRA to listen for uncaught Exceptions as this is the SendWorker process and we only send reports, we don't capture them to avoid infinite loops");
        }

        final boolean supportedAndroidVersion = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        if (!supportedAndroidVersion) {
            // NB We keep initialising so that everything is configured. But ACRA is never enabled below.
            log.w(LOG_TAG, "ACRA 5.1.0+ requires ICS or greater. ACRA is disabled and will NOT catch crashes or send messages.");
        }

        if (isInitialised()) {
            log.w(LOG_TAG, "ACRA#init called more than once. Won't do anything more.");
            return;
        }

        //noinspection ConstantConditions
        if (config == null) {
            log.e(LOG_TAG, "ACRA#init called but no CoreConfiguration provided");
            return;
        }

        final SharedPreferences prefs = new SharedPreferencesFactory(app, config).create();

        new LegacyFileHandler(app, prefs).updateToCurrentVersionIfNecessary();
        if (!senderServiceProcess) {
            // Initialize ErrorReporter with all required data
            final boolean enableAcra = supportedAndroidVersion && SharedPreferencesFactory.shouldEnableACRA(prefs);
            // Indicate that ACRA is or is not listening for crashes.
            log.i(LOG_TAG, "ACRA is " + (enableAcra ? "enabled" : "disabled") + " for " + app.getPackageName() + ", initializing...");
            SchedulerStarter schedulerStarter = new SchedulerStarter(app, config);
            ErrorReporterImpl reporter = new ErrorReporterImpl(app, config, schedulerStarter, enableAcra, supportedAndroidVersion);
            errorReporterSingleton = reporter;

            // Check for approved reports and send them (if enabled).
            // NB don't check if senderServiceProcess as it will gather these reports itself.
            if (checkReportsOnApplicationStart) {
                new ApplicationStartupProcessor(app, config, schedulerStarter).checkReports(enableAcra);
            }

            // register after initAcra is called to avoid a
            // NPE in ErrorReporter.disable() because
            // the context could be null at this moment.
            prefs.registerOnSharedPreferenceChangeListener(reporter);
        }
    }