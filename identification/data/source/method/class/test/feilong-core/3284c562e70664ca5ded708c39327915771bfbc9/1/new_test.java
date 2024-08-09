@Test
    public void testFind(){
        User zhangfei = new User("张飞", 23);
        User guanyu24 = new User("关羽", 24);
        User liubei = new User("刘备", 25);
        User guanyu50 = new User("关羽", 50);
        List<User> list = toList(zhangfei, guanyu24, liubei, guanyu50);

        assertThat(CollectionsUtil.find(list, "name", "关羽"), is(equalTo(guanyu24)));
    }