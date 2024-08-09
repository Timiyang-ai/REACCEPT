    @Test
    public void onActionClick_shouldRelayToController() {
        mManager.onActionClick(ID);

        verify(mController).onActionClick();
    }