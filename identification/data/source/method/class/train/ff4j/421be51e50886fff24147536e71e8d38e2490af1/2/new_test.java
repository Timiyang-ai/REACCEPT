@Test
    public void testDisableFeature() {
        ff4j.disable(FEATURE_FIRST);
        assertFf4j.assertNotFlipped(FEATURE_FIRST);
    }