    @Test
    public void isMobileExact() {
        assertFalse(RegexUtils.isMobileExact("11111111111"));
        assertTrue(RegexUtils.isMobileExact("13888880000"));
    }