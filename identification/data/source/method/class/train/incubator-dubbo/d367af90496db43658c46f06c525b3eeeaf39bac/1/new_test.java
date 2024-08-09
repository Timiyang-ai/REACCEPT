@Test
    public void testMergeValues() {
        List<String> merged = ConfigUtils.mergeValues(Serialization.class, "aaa,bbb,default.cunstom",
                toArray("dubbo","default.hessian2","json"));
        Assert.assertEquals(toArray("dubbo", "json", "aaa", "bbb", "default.cunstom"), merged);
    }