    @Test
    public void isEqualTo() throws Exception {
        assertTrue(V3_0.isEqualTo(of(3, 0)));
        assertFalse(V3_0.isEqualTo(of(4, 0)));
    }