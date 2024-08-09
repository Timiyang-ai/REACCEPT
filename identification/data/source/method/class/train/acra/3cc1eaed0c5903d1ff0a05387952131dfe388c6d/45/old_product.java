public static void init(Application app, ACRAConfiguration config){

        if (mApplication != null) {
            log.w(LOG_TAG, "ACRA#init called more than once. Won't do anything more.");
            return;
        }
        mApplication = app;
        
        if (config == null) {
            log.e(LOG_TAG, "ACRA#init called but no ACRAConfiguration provided");
            return;
        }
        setConfig(config);

        final SharedPreferences prefs = getACRASharedPreferences();

        try {
            checkCrashResources();

            log.d(LOG_TAG, "ACRA is enabled for " + mApplication.getPackageName() + ", intializing...");

            // Initialize ErrorReporter with all required data
            final boolean enableAcra = !shouldDisableACRA(prefs);
            final ErrorReporter errorReporter = new ErrorReporter(mApplication, prefs, enableAcra);

            // Append ReportSenders.
            errorReporter.setDefaultReportSenders();

            errorReporterSingleton = errorReporter;

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