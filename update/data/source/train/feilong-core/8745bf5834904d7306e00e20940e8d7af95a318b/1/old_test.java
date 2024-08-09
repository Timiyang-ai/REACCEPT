@Test
    public void testGroupOne(){
        List<User> list = toList(//
                        new User("张飞", 23),
                        new User("刘备", 25),
                        new User("刘备", 25));
        Map<String, User> map = CollectionsUtil.groupOne(list, "name");
        LOGGER.debug(JsonUtil.format(map));
    }