@Test
    public void testSelect(){
        List<User> list = toList(//
                        new User("张飞", 23),
                        new User("关羽", 24),
                        new User("刘备", 25));

        LOGGER.debug(JsonUtil.format(CollectionsUtil.select(list, "name", toList("张飞", "刘备"))));
    }