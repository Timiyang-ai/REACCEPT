    @Test
    public void isIP() {
        assertTrue(RegexUtils.isIP("255.255.255.0"));
        assertFalse(RegexUtils.isIP("256.255.255.0"));
    }