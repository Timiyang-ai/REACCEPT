public void updatePreferenceIntents(PreferenceGroup prefs, final String acccountType,
            Account account) {
        final PackageManager pm = mFragment.getActivity().getPackageManager();
        for (int i = 0; i < prefs.getPreferenceCount(); ) {
            Preference pref = prefs.getPreference(i);
            if (pref instanceof PreferenceGroup) {
                updatePreferenceIntents((PreferenceGroup) pref, acccountType, account);
            }
            Intent intent = pref.getIntent();
            if (intent != null) {
                // Hack. Launch "Location" as fragment instead of as activity.
                //
                // When "Location" is launched as activity via Intent, there's no "Up" button at the
                // top left, and if there's another running instance of "Location" activity, the
                // back stack would usually point to some other place so the user won't be able to
                // go back to the previous page by "back" key. Using fragment is a much easier
                // solution to those problems.
                //
                // If we set Intent to null and assign a fragment to the PreferenceScreen item here,
                // in order to make it work as expected, we still need to modify the container
                // PreferenceActivity, override onPreferenceStartFragment() and call
                // startPreferencePanel() there. In order to inject the title string there, more
                // dirty further hack is still needed. It's much easier and cleaner to listen to
                // preference click event here directly.
                if (intent.getAction().equals(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)) {
                    // The OnPreferenceClickListener overrides the click event completely. No intent
                    // will get fired.
                    pref.setOnPreferenceClickListener(new FragmentStarter(
                        LocationSettings.class.getName(), R.string.location_settings_title));
                } else {
                    ResolveInfo ri = pm.resolveActivityAsUser(intent,
                        PackageManager.MATCH_DEFAULT_ONLY, mUserHandle.getIdentifier());
                    if (ri == null) {
                        prefs.removePreference(pref);
                        continue;
                    }
                    intent.putExtra(ACCOUNT_KEY, account);
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK);
                    pref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            Intent prefIntent = preference.getIntent();
                                /*
                                 * Check the intent to see if it resolves to a exported=false
                                 * activity that doesn't share a uid with the authenticator.
                                 *
                                 * Otherwise the intent is considered unsafe in that it will be
                                 * exploiting the fact that settings has system privileges.
                                 */
                            if (isSafeIntent(pm, prefIntent, acccountType)) {
                                mFragment.getActivity().startActivityAsUser(
                                    prefIntent, mUserHandle);
                            } else {
                                Log.e(TAG,
                                    "Refusing to launch authenticator intent because"
                                        + "it exploits Settings permissions: "
                                        + prefIntent);
                            }
                            return true;
                        }
                    });
                }
            }
            i++;
        }
    }