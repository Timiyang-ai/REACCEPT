    @Test
    public void digest() throws Exception {
        Assert.assertEquals("e99a18c428cb38d5f260853678922e03", EncodingGroovyMethods.digest("abc123", "MD5"));
        Assert.assertEquals("e99a18c428cb38d5f260853678922e03", EncodingGroovyMethods.digest("abc123".getBytes("UTF-8"), "MD5"));

        // GROOVY-9049: EncodingGroovyMethods.digest() truncates hashes over 128 bits starting with 0's
        Assert.assertEquals("0ade7c2cf97f75d009975f4d720d1fa6c19f4897", EncodingGroovyMethods.digest("9", "SHA-1"));
        Assert.assertEquals("0716d9708d321ffb6a00818614779e779925365c", EncodingGroovyMethods.digest("17", "SHA-1"));
        Assert.assertEquals("0a57cb53ba59c46fc4b692527a38a87c78d84028", EncodingGroovyMethods.digest("28", "SHA-1"));
        Assert.assertEquals("0286dd552c9bea9a69ecb3759e7b94777635514b", EncodingGroovyMethods.digest("43", "SHA-1"));
        Assert.assertEquals("08a35293e09f508494096c1c1b3819edb9df50db", EncodingGroovyMethods.digest("93", "SHA-1"));


    }