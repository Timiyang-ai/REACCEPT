@Test
    public void testInitialize() {
        FileNameAnalyzer instance = new FileNameAnalyzer();
        try {
            instance.initialize();
        } catch (InitializationException ex) {
            fail(ex.getMessage());
        }
        assertTrue(instance.isEnabled());
    }