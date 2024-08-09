@Test
    public void testEnableFeature() {
        ff4j.enable(FEATURE_FIRST);
        assertFf4j.assertFlipped(FEATURE_FIRST);
    }