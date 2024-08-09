    @Test
    public void isReadyTest() {
        fullyConfigured();

        // isReady is called in changing the networkserviceprovider state to Enabled.
        when(nspvo.getState()).thenReturn(PhysicalNetworkServiceProvider.State.Disabled);

        // ssp is ready
        assertTrue(_element.isReady(nspvo));

        // If you don't call addstratospheressp api, ssp won't be ready
        when(_element._sspCredentialDao.findByZone(dataCenterId.longValue())).thenReturn(null);
        when(_element._resourceMgr.listAllHostsInOneZoneByType(Host.Type.L2Networking, dataCenterId)).thenReturn(Arrays.<HostVO> asList());
        assertFalse(_element.isReady(nspvo));
    }