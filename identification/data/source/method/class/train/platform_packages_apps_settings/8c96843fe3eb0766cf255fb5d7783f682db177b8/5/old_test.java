    @Test
    public void onReceive_toggleChanged() {
        final String key = "key";
        final Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(SettingsSliceProvider.SLICE_AUTHORITY)
                .appendPath(SettingsSlicesContract.PATH_SETTING_ACTION)
                .appendPath(key)
                .build();
        mSearchFeatureProvider.getSearchIndexableResources().getProviderValues().clear();
        insertSpecialCase(key);
        final ContentResolver resolver = mock(ContentResolver.class);
        doReturn(resolver).when(mContext).getContentResolver();
        // Turn on toggle setting
        FakeToggleController fakeToggleController = new FakeToggleController(mContext, key);
        fakeToggleController.setChecked(true);
        Intent intent = new Intent(SettingsSliceProvider.ACTION_TOGGLE_CHANGED);
        intent.putExtra(SettingsSliceProvider.EXTRA_SLICE_KEY, key);

        assertThat(fakeToggleController.isChecked()).isTrue();

        // Toggle setting
        mReceiver.onReceive(mContext, intent);

        assertThat(fakeToggleController.isChecked()).isFalse();
        verify(mFakeFeatureFactory.metricsFeatureProvider)
                .action(SettingsEnums.PAGE_UNKNOWN,
                        MetricsEvent.ACTION_SETTINGS_SLICE_CHANGED,
                        SettingsEnums.PAGE_UNKNOWN,
                        fakeToggleController.getPreferenceKey(),
                        0);
        verify(resolver).notifyChange(uri, null);
    }