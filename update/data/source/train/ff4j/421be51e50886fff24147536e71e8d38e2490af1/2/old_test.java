@Test
    public void testDisableFeature() {
        ff4j.disable("first");
        assertFf4j.assertNotFlipped("first");
    }