@Test
    public void replace(){
        assertEquals("黑色_黄色_蓝色", StringUtil.replace("黑色/黄色/蓝色", "/", "_"));
    }