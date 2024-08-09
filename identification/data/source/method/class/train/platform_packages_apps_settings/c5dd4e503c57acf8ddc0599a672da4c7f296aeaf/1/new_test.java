    @Test
    public void updateState_sendBroadcast() {
        final List<ResolveInfo> testResolveInfos = new ArrayList<>();
        testResolveInfos.add(
                getTestResolveInfo(/*isSystemApp*/ true, /*hasRequiredMetadata*/ true));
        when(mPackageManager.queryBroadcastReceivers(any(), anyInt()))
                .thenReturn(testResolveInfos);
        mController.updateState(mPreferenceCategory);
        ArgumentCaptor<Intent> intent = ArgumentCaptor.forClass(Intent.class);
        verify(mContext).sendBroadcast(intent.capture());
        assertThat(intent.getValue().getAction())
                .isEqualTo(LocationManager.SETTINGS_FOOTER_DISPLAYED_ACTION);
    }