    @Test
    public void isZh() {
        assertTrue(RegexUtils.isZh("我"));
        assertFalse(RegexUtils.isZh("wo"));
    }