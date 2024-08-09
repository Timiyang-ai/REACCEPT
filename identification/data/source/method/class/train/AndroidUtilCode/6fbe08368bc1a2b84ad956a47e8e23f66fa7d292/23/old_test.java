    @Test
    public void isURL() {
        assertTrue(RegexUtils.isURL("http://blankj.com"));
        assertFalse(RegexUtils.isURL("https:blank"));
    }