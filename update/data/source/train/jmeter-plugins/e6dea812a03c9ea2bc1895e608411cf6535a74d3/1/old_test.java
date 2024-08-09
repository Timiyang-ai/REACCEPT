@Test
    public void testGetSettingsPanel()
    {
        System.out.println("getSettingsPanel");
        BytesThroughputOverTimeGui instance = new BytesThroughputOverTimeGui();
        JSettingsPanel result = instance.getSettingsPanel();
        assertNotNull(result);
    }