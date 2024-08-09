    @Test
    public void isLessThan() throws Exception {
        assertFalse(V3_0.isLessThan(of(2, 0)));
        assertFalse(V3_0.isLessThan(of(3, 0)));
        assertTrue(V3_0.isLessThan(of(3, 1)));
        assertTrue(V3_0.isLessThan(of(4, 0)));
        assertTrue(V3_0.isLessThan(of(100, 0)));
    }