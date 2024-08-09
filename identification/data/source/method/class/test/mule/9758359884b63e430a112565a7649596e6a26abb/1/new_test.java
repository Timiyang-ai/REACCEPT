@Test
    public void testGetMuleHomeFile()
    {
        File muleHome = MuleContainerBootstrapUtils.getMuleHome();
        assertNotNull(muleHome.getAbsolutePath());
    }