    @Test
    public void isUsername() {
        assertTrue(RegexUtils.isUsername("小明233333"));
        assertFalse(RegexUtils.isUsername("小明"));
        assertFalse(RegexUtils.isUsername("小明233333_"));
    }