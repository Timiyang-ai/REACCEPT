    @Test
    public void equalsIgnoreCase() {
        assertTrue(StringUtils.equalsIgnoreCase(null, null));
        assertFalse(StringUtils.equalsIgnoreCase(null, "blankj"));
        assertTrue(StringUtils.equalsIgnoreCase("blankj", "Blankj"));
        assertTrue(StringUtils.equalsIgnoreCase("blankj", "blankj"));
        assertFalse(StringUtils.equalsIgnoreCase("blankj", "blank"));
    }