    @Test
    public void isEmail() {
        assertTrue(RegexUtils.isEmail("blankj@qq.com"));
        assertFalse(RegexUtils.isEmail("blankj@qq"));
    }