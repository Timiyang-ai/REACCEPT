    @Test
    public void toSBC() {
        assertEquals("　，．＆", StringUtils.toSBC(" ,.&"));
    }