    @Test
    public void upperFirstLetter() {
        assertEquals("Blankj", StringUtils.upperFirstLetter("blankj"));
        assertEquals("Blankj", StringUtils.upperFirstLetter("Blankj"));
        assertEquals("1Blankj", StringUtils.upperFirstLetter("1Blankj"));
    }