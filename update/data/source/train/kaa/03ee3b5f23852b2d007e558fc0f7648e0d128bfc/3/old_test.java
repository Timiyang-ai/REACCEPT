@Test
    public void testRegisterListeners() {
        DynamicLoadManager dm = new DynamicLoadManager(ldServiceMock);
        assertNotNull(dm);
        dm.registerListeners();
        
        verify(pNodeMock, atLeast(1)).addListener((OperationsNodeListener)dm);
        //verify(pNodeMock, times(1)).addListener((BootstrapNodeListener)dm);
    }