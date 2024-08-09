@Test
    public void testRemoveAll1(){
        List<User> objectCollection = new ArrayList<User>();
        objectCollection.add(new User("张飞", 23));
        objectCollection.add(new User("关羽", 24));
        objectCollection.add(new User("刘备", 25));

        LOGGER.info(JsonUtil.format(CollectionsUtil.removeAll(objectCollection, "name", "刘备")));
        LOGGER.info(JsonUtil.format(CollectionsUtil.removeAll(objectCollection, "name", "刘备", "关羽")));
    }