@Test
    public void testCollect1(){
        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        list.add(100L);

        List<String> collect1 = CollectionsUtil.collect(list, TransformerUtils.stringValueTransformer());
        LOGGER.info("list:{}", JsonUtil.format(collect1, 0, 0));
    }