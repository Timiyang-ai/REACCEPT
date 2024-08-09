    @Test
    @Config(shadows = {ShadowAccountManager.class, ShadowContentResolver.class})
    public void updatePreferenceIntents_shouldRunRecursively() {
        final PreferenceManager preferenceManager = mock(PreferenceManager.class);
        // Top level
        PreferenceGroup prefRoot = spy(new PreferenceScreen(mContext, null));
        when(prefRoot.getPreferenceManager()).thenReturn(preferenceManager);
        Preference pref1 = mock(Preference.class);
        PreferenceGroup prefGroup2 = spy(new PreferenceScreen(mContext, null));
        when(prefGroup2.getPreferenceManager()).thenReturn(preferenceManager);
        Preference pref3 = mock(Preference.class);
        PreferenceGroup prefGroup4 = spy(new PreferenceScreen(mContext, null));
        when(prefGroup4.getPreferenceManager()).thenReturn(preferenceManager);
        prefRoot.addPreference(pref1);
        prefRoot.addPreference(prefGroup2);
        prefRoot.addPreference(pref3);
        prefRoot.addPreference(prefGroup4);

        // 2nd level
        Preference pref21 = mock(Preference.class);
        Preference pref22 = mock(Preference.class);
        prefGroup2.addPreference(pref21);
        prefGroup2.addPreference(pref22);
        PreferenceGroup prefGroup41 = spy(new PreferenceScreen(mContext, null));
        when(prefGroup41.getPreferenceManager()).thenReturn(preferenceManager);
        Preference pref42 = mock(Preference.class);
        prefGroup4.addPreference(prefGroup41);
        prefGroup4.addPreference(pref42);

        // 3rd level
        Preference pref411 = mock(Preference.class);
        Preference pref412 = mock(Preference.class);
        prefGroup41.addPreference(pref411);
        prefGroup41.addPreference(pref412);

        final String acctType = "testType";
        mPrefLoader.updatePreferenceIntents(prefRoot, acctType, mAccount);

        verify(mPrefLoader).updatePreferenceIntents(prefGroup2, acctType, mAccount);
        verify(mPrefLoader).updatePreferenceIntents(prefGroup4, acctType, mAccount);
        verify(mPrefLoader).updatePreferenceIntents(prefGroup41, acctType, mAccount);
    }