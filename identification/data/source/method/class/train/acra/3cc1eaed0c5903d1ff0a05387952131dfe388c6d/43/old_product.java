public static void init(Application app) {
        mAppStartDate = new Time();
        mAppStartDate.setToNow();
        mApplication = app;
        mReportsCrashes = mApplication.getClass().getAnnotation(ReportsCrashes.class);
        if (mReportsCrashes != null) {

            final SharedPreferences prefs = getACRASharedPreferences();
            Log.d(ACRA.LOG_TAG, "Set OnSharedPreferenceChangeListener.");
            // We HAVE to keep a reference otherwise the listener could be
            // garbage collected:
            // http://stackoverflow.com/questions/2542938/sharedpreferences-onsharedpreferencechangelistener-not-being-called-consistently/3104265#3104265
            mPrefListener = new OnSharedPreferenceChangeListener() {

                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (PREF_DISABLE_ACRA.equals(key) || PREF_ENABLE_ACRA.equals(key)) {
                        boolean disableAcra = false;
                        try {
                            final boolean enableAcra = sharedPreferences.getBoolean(PREF_ENABLE_ACRA, true);
                            disableAcra = sharedPreferences.getBoolean(PREF_DISABLE_ACRA, !enableAcra);
                        } catch (Exception e) {
                            // In case of a ClassCastException
                        }

                        if (disableAcra) {
                            ErrorReporter.getInstance().disable();
                        } else {
                            try {
                                initAcra(); // TODO if PREF_DISABLE_ACRA or PREF_ENABLE_ACRA is changed the we might call #initAcra again which could be disastrous.
                            } catch (ACRAConfigurationException e) {
                                Log.w(LOG_TAG, "Error : ", e);
                            }
                        }
                    }
                }
            };

            // If the application default shared preferences contains true for
            // the key "acra.disable", do not activate ACRA. Also checks the
            // alternative opposite setting "acra.enable" if "acra.disable" is
            // not found.
            boolean disableAcra = false;
            try {
                final boolean enableAcra = prefs.getBoolean(PREF_ENABLE_ACRA, true);
                disableAcra = prefs.getBoolean(PREF_DISABLE_ACRA, !enableAcra);
            } catch (Exception e) {
                // In case of a ClassCastException
            }

            if (disableAcra) {
                Log.d(LOG_TAG, "ACRA is disabled for " + mApplication.getPackageName() + ".");
            } else {
                try {
                    initAcra();
                } catch (ACRAConfigurationException e) {
                    Log.w(LOG_TAG, "Error : ", e);
                }
            }

            // This listener has to be set after initAcra is called to avoid a
            // NPE in ErrorReporter.disable() because
            // the context could be null at this moment.
            prefs.registerOnSharedPreferenceChangeListener(mPrefListener);
        }
    }