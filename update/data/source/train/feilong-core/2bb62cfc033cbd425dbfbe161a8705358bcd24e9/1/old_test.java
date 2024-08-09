@Test
    public void testGroup(){
        Integer[] array = { 1, 1, 1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8 };

        Map<Integer, List<Integer>> group = ArrayUtil.group(array);
        LOGGER.debug(JsonUtil.format(group));

        Collection<List<Integer>> values = group.values();
        LOGGER.debug(JsonUtil.format(values));

        //****************************************************************
        String[] array1 = { "关羽", "feilong", "关羽", "基友团", "关羽" };

        Map<String, List<String>> group1 = ArrayUtil.group(array1);
        LOGGER.debug(JsonUtil.format(group1));

        Collection<List<String>> values1 = group1.values();
        LOGGER.debug(JsonUtil.format(values1));
    }