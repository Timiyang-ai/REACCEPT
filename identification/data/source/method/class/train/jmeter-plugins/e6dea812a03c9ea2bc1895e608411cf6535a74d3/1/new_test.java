@Test
    public void testGetSettingsPanel()
    {
        System.out.println("getSettingsPanel");
        BytesThroughputOverTimeGui instance = new BytesThroughputOverTimeGui();
        JSettingsPanel result = instance.createSettingsPanel();
        assertNotNull(result);
    }