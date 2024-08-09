    @Test
    public void md5() throws Exception {
        Assert.assertEquals("e99a18c428cb38d5f260853678922e03", EncodingGroovyMethods.md5("abc123"));
        Assert.assertEquals("e99a18c428cb38d5f260853678922e03", EncodingGroovyMethods.md5("abc123".getBytes("UTF-8")));
    }