    @Test
    public void toDBC() {
        assertEquals(" ,.&", StringUtils.toDBC("　，．＆"));
    }