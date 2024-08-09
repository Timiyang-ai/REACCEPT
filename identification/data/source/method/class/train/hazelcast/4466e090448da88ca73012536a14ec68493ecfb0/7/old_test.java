    @Test
    public void isUnknownOrLessThan() throws Exception {
        assertFalse(V3_0.isUnknownOrLessThan(of(2, 0)));
        assertFalse(V3_0.isUnknownOrLessThan(of(3, 0)));
        assertTrue(V3_0.isUnknownOrLessThan(of(3, 1)));
        assertTrue(V3_0.isUnknownOrLessThan(of(4, 0)));
        assertTrue(V3_0.isUnknownOrLessThan(of(100, 0)));
        assertTrue(UNKNOWN.isUnknownOrLessThan(of(100, 0)));
    }