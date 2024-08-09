@Test
    public void testSelectRejected1(){
        List<User> list = toList(//
                        new User("张飞", 23),
                        new User("关羽", 24),
                        new User("刘备", 25));
        List<User> selectRejected = CollectionsUtil.selectRejected(list, "name", "刘备", "张飞");
        assertSame(1, selectRejected.size());
        assertThat(selectRejected.get(0), hasProperty("name", equalTo("关羽")));
    }