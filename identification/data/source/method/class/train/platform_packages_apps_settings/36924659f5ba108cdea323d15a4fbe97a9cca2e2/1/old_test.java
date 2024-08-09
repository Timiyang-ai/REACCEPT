    @Test
    public void displayTilesAsPreference_shouldAddTilesWithIntent() {
        when(mFakeFeatureFactory.dashboardFeatureProvider
                .getTilesForCategory(nullable(String.class)))
                .thenReturn(mDashboardCategory);
        when(mFakeFeatureFactory.dashboardFeatureProvider
                .getDashboardKeyForTile(nullable(Tile.class)))
                .thenReturn("test_key");
        mTestFragment.onCreatePreferences(new Bundle(), "rootKey");

        verify(mTestFragment.mScreen).addPreference(nullable(Preference.class));
    }