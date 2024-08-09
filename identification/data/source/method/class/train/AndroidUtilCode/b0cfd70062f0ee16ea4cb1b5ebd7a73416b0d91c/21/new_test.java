    @Test
    public void length() {
        assertEquals(0, StringUtils.length(null));
        assertEquals(0, StringUtils.length(""));
        assertEquals(6, StringUtils.length("blankj"));
    }