@Test
    public void testInitialize() {
        FileNameAnalyzer instance = new FileNameAnalyzer();
        try {
            instance.initializeSettings(getSettings());
            instance.initialize(null);
        } catch (InitializationException ex) {
            fail(ex.getMessage());
        }
        assertTrue(instance.isEnabled());
    }