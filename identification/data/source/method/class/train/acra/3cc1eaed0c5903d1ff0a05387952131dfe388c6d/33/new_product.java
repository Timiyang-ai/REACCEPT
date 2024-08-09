public static void init(Application app, ACRAConfiguration config, boolean checkReportsOnApplicationStart){

        final boolean senderServiceProcess = isACRASenderServiceProcess(app);
        if (senderServiceProcess) {
            log.i(LOG_TAG, "Not initialising ACRA to listen for uncaught Exceptions as this is the SendWorker process and we only send reports, we don't capture them to avoid infinite loops");
        }

        boolean supportedAndroidVersion = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO);
        if (!supportedAndroidVersion){
            log.w(LOG_TAG, "ACRA 4.7.0+ requires Froyo or greater. ACRA is disabled and will NOT catch crashes or send messages.");
        }

        if (mApplication != null) {
            log.w(LOG_TAG, "ACRA#init called more than once. Won't do anything more.");
            return;
        }
        mApplication = app;
        
        if (config == null) {
            log.e(LOG_TAG, "ACRA#init called but no ACRAConfiguration provided");
            return;
        }
        configProxy = config;

        final SharedPreferences prefs = new SharedPreferencesFactory(mApplication, configProxy).create();

        try {
            config.checkCrashResources();

            // Check prefs to see if we have converted from legacy (pre 4.8.0) ACRA
            if (!prefs.getBoolean(PREF__LEGACY_ALREADY_CONVERTED_TO_4_8_0, false)) {
                log.i(LOG_TAG, "Migrating unsent ACRA reports to new file locations");
                // If not then move reports to approved/unapproved folders and mark as converted.
                new ReportMigrator(app).migrate();

                // Mark as converted.
                final SharedPreferences.Editor editor = prefs.edit().putBoolean(PREF__LEGACY_ALREADY_CONVERTED_TO_4_8_0, true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    editor.apply();
                } else {
                    editor.commit(); // Synchronous, so avoid it if we are SDK 9 or above.
                }
            }

            // Initialize ErrorReporter with all required data
            final boolean enableAcra = supportedAndroidVersion && !shouldDisableACRA(prefs);
            log.d(LOG_TAG, "ACRA is " + (enableAcra ? "enabled" : "disabled") + " for " + mApplication.getPackageName() + ", initializing...");
            errorReporterSingleton = new ErrorReporter(mApplication, configProxy, prefs, enableAcra, supportedAndroidVersion, !senderServiceProcess);

            // Check for pending reports and send them (if enabled).
            // NB don't check if senderServiceProcess as it will gather these reports itself.
            if (checkReportsOnApplicationStart && !senderServiceProcess) {
                final AvailableReportChecker checker = new AvailableReportChecker(mApplication,  config, enableAcra);
                checker.execute();
            }

        } catch (ACRAConfigurationException e) {
            log.w(LOG_TAG, "Error : ", e);
        }

        // We HAVE to keep a reference otherwise the listener could be garbage
        // collected:
        // http://stackoverflow.com/questions/2542938/sharedpreferences-onsharedpreferencechangelistener-not-being-called-consistently/3104265#3104265
        mPrefListener = new OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (PREF_DISABLE_ACRA.equals(key) || PREF_ENABLE_ACRA.equals(key)) {
                    final boolean enableAcra = !shouldDisableACRA(sharedPreferences);
                    getErrorReporter().setEnabled(enableAcra);
                }
            }
        };

        // This listener has to be set after initAcra is called to avoid a
        // NPE in ErrorReporter.disable() because
        // the context could be null at this moment.
        prefs.registerOnSharedPreferenceChangeListener(mPrefListener);
    }