    @Test
    public void toJson() throws Exception {
        Assert.assertEquals(simpleObject, CodecTools.toJson(simpleString));
    }