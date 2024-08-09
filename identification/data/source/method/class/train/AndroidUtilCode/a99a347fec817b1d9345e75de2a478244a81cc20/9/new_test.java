    @Test
    public void equals() {
        assertTrue(StringUtils.equals(null, null));
        assertTrue(StringUtils.equals("blankj", "blankj"));
        assertFalse(StringUtils.equals("blankj", "Blankj"));
    }