@Test
    public void testSelectRejected1(){
        User zhangfei = new User("张飞", 23);
        User guanyu = new User("关羽", 24);
        User liubei = new User("刘备", 25);
        List<User> list = toList(zhangfei, guanyu, liubei);

        List<User> selectRejected = CollectionsUtil.selectRejected(list, "name", "刘备", "张飞");
        assertSame(1, selectRejected.size());
        assertThat(selectRejected.get(0), hasProperty("name", equalTo("关羽")));
    }