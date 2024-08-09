@Test
    public void testInitialize() {
        FileNameAnalyzer instance = new FileNameAnalyzer();
        try {
            instance.initialize(getSettings());
            instance.prepare(null);
        } catch (InitializationException ex) {
            fail(ex.getMessage());
        }
        assertTrue(instance.isEnabled());
    }