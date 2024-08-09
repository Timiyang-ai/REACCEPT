@Test
    public void testGetMuleHomeFile()
    {
        File muleHome = MuleContainerBootstrapUtils.getMuleHomeFile();
        assertNotNull(muleHome.getAbsolutePath());
    }