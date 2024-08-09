    @Test
    void isNull() {
        assertNotNull(instance.isNull());
        assertTrue(instance.isNull(null));
    }