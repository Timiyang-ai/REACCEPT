@Override
    public void updateState(Preference preference) {
        PreferenceCategory category = (PreferenceCategory) preference;
        category.removeAll();
        mFooterInjectors.clear();
        Collection<FooterData> footerData = getFooterData();
        for (FooterData data : footerData) {
            // Generate a footer preference with the given text
            FooterPreference footerPreference = new FooterPreference(preference.getContext());
            String footerString;
            try {
                footerString =
                        mPackageManager
                                .getResourcesForApplication(data.applicationInfo)
                                .getString(data.footerStringRes);
            } catch (NameNotFoundException exception) {
                if (Log.isLoggable(TAG, Log.WARN)) {
                    Log.w(
                            TAG,
                            "Resources not found for application "
                                    + data.applicationInfo.packageName);
                }
                continue;
            }
            footerPreference.setTitle(footerString);
            // Inject the footer
            category.addPreference(footerPreference);
            // Send broadcast to the injector announcing a footer has been injected
            sendBroadcastFooterDisplayed(data.componentName);
            mFooterInjectors.add(data.componentName);
        }
    }