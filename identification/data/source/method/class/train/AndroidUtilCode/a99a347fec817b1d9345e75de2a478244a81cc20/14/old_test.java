    @Test
    public void isTrimEmpty() {
        assertTrue(StringUtils.isTrimEmpty(""));
        assertTrue(StringUtils.isTrimEmpty(null));
        assertTrue(StringUtils.isTrimEmpty(" "));
    }