@Test
    public void testMergeValues() {
        List<String> merged = ConfigUtils.mergeValues(Serialization.class, "aaa,bbb,default.cunstom",
                                                      Arrays.asList(new String[]{"dubbo","default.hessian2","json"}));
        Assert.assertEquals("[dubbo, json, aaa, bbb, default.cunstom]",merged.toString());
    }