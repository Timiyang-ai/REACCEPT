@Test
    public void testGetMuleAppsFile()
    {
        File muleApps = MuleContainerBootstrapUtils.getMuleAppsFile(); 
        assertNotNull(muleApps.getAbsolutePath());
    }