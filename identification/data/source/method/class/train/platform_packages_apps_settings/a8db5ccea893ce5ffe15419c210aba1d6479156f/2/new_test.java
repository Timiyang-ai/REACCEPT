    @Test
    public void maybeEnforceRestrictions_noRestrictions() {
        // GIVEN there are no restrictions set...
        when(mRestrictionUtils.checkIfRestrictionEnforced(any(Context.class), any(String.class)))
                .thenReturn(null);

        // WHEN the maybeEnforceRestrictions is called...
        // THEN false is returned to indicate there was no restriction to enforce
        assertThat(mBluetoothEnabler.maybeEnforceRestrictions()).isFalse();

        // THEN a null EnfoceAdmin is set.
        verify(mSwitchController).setDisabledByAdmin(null);
        // THEN the state of the switch isn't changed.
        verify(mSwitchController, never()).setChecked(anyBoolean());
    }