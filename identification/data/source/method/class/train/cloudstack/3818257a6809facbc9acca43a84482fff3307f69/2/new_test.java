    @Test
    public void canHandleTest() {
        fullyConfigured();

        // ssp is active
        assertTrue(_element.canHandle(psvo));

        // You can disable ssp temporary by truning the state disabled
        when(nspvo.getState()).thenReturn(PhysicalNetworkServiceProvider.State.Disabled);
        assertFalse(_element.canHandle(psvo));

        // If you don't want ssp for a specific physicalnetwork, you don't need to
        // setup physicalNetworkProvider.
        when(_element._physicalNetworkServiceProviderDao.findByServiceProvider(physicalNetworkId, "StratosphereSsp")).thenReturn(null);
        assertFalse(_element.canHandle(psvo));

        // restore...
        when(nspvo.getState()).thenReturn(PhysicalNetworkServiceProvider.State.Enabled);
        when(_element._physicalNetworkServiceProviderDao.findByServiceProvider(physicalNetworkId, "StratosphereSsp")).thenReturn(nspvo);

        // If you don't call addstratospheressp api, ssp won't be active
        when(_element._sspCredentialDao.findByZone(dataCenterId.longValue())).thenReturn(null);
        when(_element._resourceMgr.listAllHostsInOneZoneByType(Host.Type.L2Networking, dataCenterId)).thenReturn(Arrays.<HostVO> asList());
        assertFalse(_element.canHandle(psvo));
    }