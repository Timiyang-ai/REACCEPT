    @Test
    public void getConnectedDevicesSummaryResourceId_NFCAndDrivingModeAvailable() {
        // NFC available, driving mode available
        mShadowNfcAdapter.setEnabled(true);
        assertThat(AdvancedConnectedDeviceController
                .getConnectedDevicesSummaryResourceId(mNfcController, true))
                .isEqualTo(R.string.connected_devices_dashboard_summary);
    }