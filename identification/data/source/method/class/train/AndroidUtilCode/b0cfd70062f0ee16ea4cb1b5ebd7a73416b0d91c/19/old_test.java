    @Test
    public void isSpace() {
        assertTrue(StringUtils.isSpace(""));
        assertTrue(StringUtils.isSpace(null));
        assertTrue(StringUtils.isSpace(" "));
        assertTrue(StringUtils.isSpace("　 \n\t\r"));
    }