    @Test
    public void canShareNetwork_shouldInvisibleIfWithoutConfiguration() {
        setUpForConnectedNetwork();
        when(mockAccessPoint.getConfig()).thenReturn(null);

        displayAndResume();

        verify(mockButtonsPref).setButton4Visible(false);
    }