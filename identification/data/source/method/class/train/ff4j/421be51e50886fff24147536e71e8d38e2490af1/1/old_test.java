@Test
    public void testEnableFeature() {
        ff4j.enable("first");
        assertFf4j.assertFlipped("first");
    }