    @Test
    public void isGreaterOrEqual() throws Exception {
        assertTrue(V3_0.isGreaterOrEqual(of(2, 0)));
        assertTrue(V3_0.isGreaterOrEqual(of(3, 0)));
        assertTrue(V3_0.isGreaterOrEqual(of(3, 0)));
        assertFalse(V3_0.isGreaterOrEqual(of(4, 0)));
    }