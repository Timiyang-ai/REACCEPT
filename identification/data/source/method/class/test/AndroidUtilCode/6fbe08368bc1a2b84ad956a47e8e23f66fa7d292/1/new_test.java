    @Test
    public void isMatch() {
        assertTrue(RegexUtils.isMatch("\\d?", "1"));
        assertFalse(RegexUtils.isMatch("\\d?", "a"));
    }