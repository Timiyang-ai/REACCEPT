    @Test
    public void lowerFirstLetter() {
        assertEquals("blankj", StringUtils.lowerFirstLetter("blankj"));
        assertEquals("blankj", StringUtils.lowerFirstLetter("Blankj"));
        assertEquals("1blankj", StringUtils.lowerFirstLetter("1blankj"));
    }