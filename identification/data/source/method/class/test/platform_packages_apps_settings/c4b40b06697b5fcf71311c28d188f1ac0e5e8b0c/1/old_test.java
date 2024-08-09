    @Test
    public void hasEthernet_shouldQueryEthernetSummaryForUser() throws Exception {
        when(mManager.isNetworkSupported(anyInt())).thenReturn(true);
        final String subscriber = "TestSub";
        when(mTelephonyManager.getSubscriberId()).thenReturn(subscriber);

        DataUsageUtils.hasEthernet(mContext);

        verify(mNetworkStatsManager).querySummaryForUser(eq(ConnectivityManager.TYPE_ETHERNET),
                eq(subscriber), anyLong() /* startTime */, anyLong() /* endTime */);
    }