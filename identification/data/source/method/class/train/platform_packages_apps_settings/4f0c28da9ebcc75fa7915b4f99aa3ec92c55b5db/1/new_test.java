    @Test
    public void updateAllPreferenceCategory_containCorrectPreference() {
        mNetworkSelectSettings.updateAllPreferenceCategory();

        assertThat(mConnectedPreferenceCategory.getPreferenceCount()).isEqualTo(1);
        final NetworkOperatorPreference connectedPreference =
                (NetworkOperatorPreference) mConnectedPreferenceCategory.getPreference(0);
        assertThat(connectedPreference.getCellInfo()).isEqualTo(mCellInfo1);
        assertThat(mPreferenceCategory.getPreferenceCount()).isEqualTo(1);
        final NetworkOperatorPreference preference =
                (NetworkOperatorPreference) mPreferenceCategory.getPreference(0);
        assertThat(preference.getCellInfo()).isEqualTo(mCellInfo2);
    }