@Test
    public void testCollect1(){
        List<Long> list = null;
        List<String> collect1 = CollectionsUtil.collect(list, TransformerUtils.stringValueTransformer());
        LOGGER.info("list:{}", JsonUtil.format(collect1, 0, 0));
    }