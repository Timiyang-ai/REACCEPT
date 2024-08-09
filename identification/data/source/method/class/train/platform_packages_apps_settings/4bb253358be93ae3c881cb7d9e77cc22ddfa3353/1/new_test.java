    @Test
    public void getSummary_twoSubsOneDefaultForEverythingDataActive() {
        setupMockSubscriptions(2);

        ShadowSubscriptionManager.setDefaultSmsSubscriptionId(11);
        ShadowSubscriptionManager.setDefaultVoiceSubscriptionId(11);
        when(mTelephonyManager.isDataEnabled()).thenReturn(true);
        when(mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)).thenReturn(true);

        assertThat(mController.getSummary(11, true)).isEqualTo(
                mContext.getString(R.string.default_for_calls_and_sms) + System.lineSeparator()
                        + mContext.getString(R.string.mobile_data_active));

        assertThat(mController.getSummary(22, false)).isEqualTo(
                mContext.getString(R.string.subscription_available));
    }