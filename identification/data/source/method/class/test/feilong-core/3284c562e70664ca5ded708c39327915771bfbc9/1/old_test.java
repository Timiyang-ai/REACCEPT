@Test
    public void testFind(){
        List<User> list = toList(//
                        new User("张飞", 23),
                        new User("关羽", 24),
                        new User("刘备", 25),
                        new User("关羽", 24));
        LOGGER.debug(JsonUtil.format(CollectionsUtil.find(list, "name", "关羽")));
    }