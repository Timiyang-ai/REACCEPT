    @Test
    public void isGreaterThan() throws Exception {
        assertTrue(V3_0.isGreaterThan(of(2, 0)));
        assertFalse(V3_0.isGreaterThan(of(3, 0)));
        assertFalse(V3_0.isGreaterThan(of(4, 0)));
    }