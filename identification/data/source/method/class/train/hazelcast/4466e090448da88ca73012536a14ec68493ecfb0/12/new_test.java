    @Test
    public void isUnknownOrGreaterThan() throws Exception {
        assertTrue(V3_0.isUnknownOrGreaterThan(of(2, 0)));
        assertFalse(V3_0.isUnknownOrGreaterThan(of(3, 0)));
        assertFalse(V3_0.isUnknownOrGreaterThan(of(4, 0)));
        assertTrue(UNKNOWN.isUnknownOrGreaterThan(of(4, 0)));
    }