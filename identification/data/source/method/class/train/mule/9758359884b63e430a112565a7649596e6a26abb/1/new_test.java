@Test
    public void testGetMuleAppsFile()
    {
        File muleApps = MuleContainerBootstrapUtils.getMuleAppsDir();
        assertNotNull(muleApps.getAbsolutePath());
    }