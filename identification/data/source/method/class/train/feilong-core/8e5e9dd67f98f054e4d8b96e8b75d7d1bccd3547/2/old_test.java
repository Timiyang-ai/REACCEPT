@Test
    public void testGroupCount(){
        List<User> testList = new ArrayList<User>();
        testList.add(new User("张飞", 23));
        testList.add(new User("关羽", 24));
        testList.add(new User("刘备", 25));

        Map<String, Integer> map = CollectionsUtil.groupCount(testList, null, "name");
        LOGGER.info(JsonUtil.format(map));

        map = CollectionsUtil.groupCount(testList, new BeanPropertyValueEqualsPredicate<User>("name", "刘备"), "name");
        LOGGER.info(JsonUtil.format(map));
    }