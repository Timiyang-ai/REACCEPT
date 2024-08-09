public static void init(@NonNull Application app, @NonNull ACRAConfiguration config, boolean checkReportsOnApplicationStart){

        final boolean senderServiceProcess = isACRASenderServiceProcess();
        if (senderServiceProcess) {
            if (ACRA.DEV_LOGGING) log.d(LOG_TAG, "Not initialising ACRA to listen for uncaught Exceptions as this is the SendWorker process and we only send reports, we don't capture them to avoid infinite loops");
        }

        final boolean supportedAndroidVersion = Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
        if (!supportedAndroidVersion){
            // NB We keep initialising so that everything is configured. But ACRA is never enabled below.
            log.w(LOG_TAG, "ACRA 4.7.0+ requires Froyo or greater. ACRA is disabled and will NOT catch crashes or send messages.");
        }

        if (mApplication != null) {
            log.w(LOG_TAG, "ACRA#init called more than once. Won't do anything more.");
            return;
        }
        mApplication = app;

        //noinspection ConstantConditions
        if (config == null) {
            log.e(LOG_TAG, "ACRA#init called but no ACRAConfiguration provided");
            return;
        }
        configProxy = config;

        final SharedPreferences prefs = new SharedPreferencesFactory(mApplication, configProxy).create();

        new LegacyFileHandler(app, prefs).updateToCurrentVersionIfNecessary();

        // Initialize ErrorReporter with all required data
        final boolean enableAcra = supportedAndroidVersion && !shouldDisableACRA(prefs);
        if (!senderServiceProcess) {
            // Indicate that ACRA is or is not listening for crashes.
            log.i(LOG_TAG, "ACRA is " + (enableAcra ? "enabled" : "disabled") + " for " + mApplication.getPackageName() + ", initializing...");
        }
        errorReporterSingleton = new ErrorReporter(mApplication, configProxy, prefs, enableAcra, supportedAndroidVersion, !senderServiceProcess);

        // Check for approved reports and send them (if enabled).
        // NB don't check if senderServiceProcess as it will gather these reports itself.
        if (checkReportsOnApplicationStart && !senderServiceProcess) {
            final ApplicationStartupProcessor startupProcessor = new ApplicationStartupProcessor(mApplication,  config);
            if (config.deleteOldUnsentReportsOnApplicationStart()) {
                startupProcessor.deleteUnsentReportsFromOldAppVersion();
            }
            if (config.deleteUnapprovedReportsOnApplicationStart()) {
                startupProcessor.deleteAllUnapprovedReportsBarOne();
            }
            if (enableAcra) {
                startupProcessor.sendApprovedReports();
            }
        }

        // We HAVE to keep a reference otherwise the listener could be garbage
        // collected:
        // http://stackoverflow.com/questions/2542938/sharedpreferences-onsharedpreferencechangelistener-not-being-called-consistently/3104265#3104265
        mPrefListener = new OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(@NonNull SharedPreferences sharedPreferences, String key) {
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