    @Test
    public void repeatOptionalDecimals() {
        assertEquals("0.00000001", formatRepeat(SATOSHI, 2, 4));
        assertEquals("0.00000010", formatRepeat(SATOSHI.multiply(10), 2, 4));
        assertEquals("0.01", formatRepeat(CENT, 2, 4));
        assertEquals("0.10", formatRepeat(CENT.multiply(10), 2, 4));

        assertEquals("0", formatRepeat(SATOSHI, 2, 2));
        assertEquals("0", formatRepeat(SATOSHI.multiply(10), 2, 2));
        assertEquals("0.01", formatRepeat(CENT, 2, 2));
        assertEquals("0.10", formatRepeat(CENT.multiply(10), 2, 2));

        assertEquals("0", formatRepeat(CENT, 2, 0));
        assertEquals("0", formatRepeat(CENT.multiply(10), 2, 0));
    }