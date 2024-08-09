    @Test
    public void canForgetNetwork_shouldInvisibleIfWithoutConfiguration() {
        setUpForConnectedNetwork();
        when(mockAccessPoint.getConfig()).thenReturn(null);
        mController = newWifiDetailPreferenceController();

        displayAndResume();

        verify(mockButtonsPref).setButton1Visible(false);
    }