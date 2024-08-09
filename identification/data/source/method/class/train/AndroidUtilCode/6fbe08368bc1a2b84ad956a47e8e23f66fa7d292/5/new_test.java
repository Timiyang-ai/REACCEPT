    @Test
    public void isIDCard18() {
        assertTrue(RegexUtils.isIDCard18("33698418400112523x"));
        assertTrue(RegexUtils.isIDCard18("336984184001125233"));
        assertFalse(RegexUtils.isIDCard18("336984184021125233"));
    }