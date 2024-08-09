    @Test
    public void setDeviceName_preferenceUpdatedWhenDeviceNameUpdated() {
        acceptDeviceName(true);
        mController.displayPreference(mScreen);
        mController.onPreferenceChange(mPreference, TESTING_STRING);

        assertThat(mPreference.getSummary()).isEqualTo(TESTING_STRING);
    }