    @Test
    public void forgetNetwork_ephemeral() {
        setUpForConnectedNetwork();
        String ssid = "ssid";
        when(mockWifiInfo.isEphemeral()).thenReturn(true);
        when(mockWifiInfo.getSSID()).thenReturn(ssid);

        displayAndResume();
        mForgetClickListener.getValue().onClick(null);

        verify(mockWifiManager).disableEphemeralNetwork(ssid);
        verify(mockMetricsFeatureProvider)
                .action(mockActivity, MetricsProto.MetricsEvent.ACTION_WIFI_FORGET);
    }