    @Test
    public void isDate() {
        assertTrue(RegexUtils.isDate("2016-08-16"));
        assertTrue(RegexUtils.isDate("2016-02-29"));
        assertFalse(RegexUtils.isDate("2015-02-29"));
        assertFalse(RegexUtils.isDate("2016-8-16"));
    }